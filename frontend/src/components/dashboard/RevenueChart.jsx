import { useEffect, useState } from "react";
import { getRevenueData } from "../../services/dashboardService";

import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  ResponsiveContainer
} from "recharts";

import "../../styles/chart.css";

function RevenueChart() {

  const [data, setData] = useState([]);

  useEffect(() => {
    getRevenueData()
      .then((res) => setData(res.data))
      .catch((err) => console.log(err));
  }, []);

  return (
    <div className="chart-card">

      <div className="chart-header">
        <h2>Revenue Analytics</h2>
        <p>Monthly earnings overview</p>
      </div>

      <ResponsiveContainer width="100%" height={260}>
        <LineChart data={data}>

          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="month" />
          <YAxis />
          <Tooltip />

          <Line
            type="monotone"
            dataKey="revenue"
            stroke="#2563eb"
            strokeWidth={3}
            dot={{ r: 3 }}
          />

        </LineChart>
      </ResponsiveContainer>

    </div>
  );
}

export default RevenueChart;