package com.harshitha.freelancemanager.service;

import com.harshitha.freelancemanager.entity.Freelancer;
import com.harshitha.freelancemanager.repository.FreelancerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.harshitha.freelancemanager.dto.FreelancerRequestDto;
import com.harshitha.freelancemanager.dto.FreelancerResponseDto;
import com.harshitha.freelancemanager.exception.FreelancerNotFoundException;
import java.util.List;

@Service
public class FreelancerService {

    @Autowired
    private FreelancerRepository freelancerRepository;

    public List<FreelancerResponseDto> getAllFreelancers() {

        return freelancerRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }
     public FreelancerResponseDto addFreelancer(
             FreelancerRequestDto dto) {

         Freelancer freelancer = convertToEntity(dto);

         Freelancer saved =
                 freelancerRepository.save(freelancer);

         return convertToDto(saved);
     }
    public FreelancerResponseDto getFreelancerById(Long id) {

        Freelancer freelancer = freelancerRepository.findById(id)
                .orElseThrow(() ->
                        new FreelancerNotFoundException(
                                "Freelancer not found with id: " + id
                        )
                );

        return convertToDto(freelancer);
    }

    public FreelancerResponseDto updateFreelancer(Long id, FreelancerRequestDto dto) {

        Freelancer freelancer = freelancerRepository.findById(id)
                .orElseThrow(() ->
                        new FreelancerNotFoundException("Freelancer not found with id: " + id)
                );

        freelancer.setName(dto.getName());
        freelancer.setSkill(dto.getSkill());
        freelancer.setSalary(dto.getSalary());

        Freelancer updated = freelancerRepository.save(freelancer);

        return convertToDto(updated);
    }

    public String deleteFreelancer(Long id) {

        if (!freelancerRepository.existsById(id)) {
            throw new RuntimeException("Freelancer not found with id: " + id);
        }

        freelancerRepository.deleteById(id);

        return "Freelancer deleted successfully";
    }

    public List<FreelancerResponseDto> searchBySkill(String skill) {
        return freelancerRepository.findBySkill(skill)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public List<FreelancerResponseDto> searchByName(String name) {
        return freelancerRepository.findByName(name)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public List<FreelancerResponseDto> searchByNamePartial(String name) {
        return freelancerRepository.findByNameContaining(name)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public List<FreelancerResponseDto> searchBySkillPartial(String skill) {
        return freelancerRepository.findBySkillContaining(skill)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public List<FreelancerResponseDto> searchBySalary(Double salary) {
        return freelancerRepository.findBySalary(salary)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public List<FreelancerResponseDto> searchByNameAndSkill(String name, String skill) {
        return freelancerRepository.findByNameAndSkill(name, skill)
                .stream()
                .map(this::convertToDto)
                .toList();
    }
    public List<FreelancerResponseDto> searchByNameIgnoreCase(String name) {
        return freelancerRepository.findByNameIgnoreCase(name)
                .stream()
                .map(this::convertToDto)
                .toList();
    }
    public List<FreelancerResponseDto> searchBySalaryBetween(
            Double minSalary,
            Double maxSalary) {

        return freelancerRepository.findBySalaryBetween(minSalary, maxSalary)
                .stream()
                .map(this::convertToDto)
                .toList();
    }
    public List<FreelancerResponseDto> searchByNameIgnoreCasePartial(String name) {
        return freelancerRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::convertToDto)
                .toList();
    }
    public List<FreelancerResponseDto> getFreelancersSorted(
            String field,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(field).descending()
                : Sort.by(field).ascending();

        return freelancerRepository.findAll(sort)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public Page<FreelancerResponseDto> getFreelancersPaginated(
            int page,
            int size,
            String field,
            String direction) {

        Sort sort = Sort.by(
                Sort.Direction.fromString(direction),
                field
        );

        Page<Freelancer> freelancerPage =
                freelancerRepository.findAll(PageRequest.of(page, size, sort));

        return freelancerPage.map(this::convertToDto);
    }
    public Page<FreelancerResponseDto> filterBySkill(
            String skill,
            int page,
            int size,
            String sortField,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortField).descending()
                : Sort.by(sortField).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Freelancer> freelancerPage =
                freelancerRepository.findBySkill(skill, pageable);

        return freelancerPage.map(this::convertToDto);
    }

    //DTO PART
    private FreelancerResponseDto convertToDto(
            Freelancer freelancer) {

        FreelancerResponseDto dto =
                new FreelancerResponseDto();

        dto.setId(freelancer.getId());
        dto.setName(freelancer.getName());
        dto.setSkill(freelancer.getSkill());
        dto.setSalary(freelancer.getSalary());

        return dto;
    }
    private Freelancer convertToEntity(
            FreelancerRequestDto dto) {

        Freelancer freelancer = new Freelancer();

        freelancer.setName(dto.getName());
        freelancer.setSkill(dto.getSkill());
        freelancer.setSalary(dto.getSalary());

        return freelancer;
    }
}