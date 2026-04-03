import React, { useEffect, useState } from "react";
import {
    LineChart, Line, XAxis, YAxis, Tooltip, CartesianGrid,
    PieChart, Pie, Cell, Legend
} from "recharts";

function App() {

    const [packets, setPackets] = useState([]);
    const [search, setSearch] = useState("");

    // Stats
    const totalPackets = packets.length;
    const tcpCount = packets.filter(p => p.protocol?.toUpperCase() === "TCP").length;
    const udpCount = packets.filter(p => p.protocol?.toUpperCase() === "UDP").length;
    const cardStyle = {
        backgroundColor: "#1e1e1e",
        padding: "20px",
        borderRadius: "10px",
        width: "200px",
        textAlign: "center"
    };

    useEffect(() => {

        const fetchData = () => {
            fetch("http://localhost:8080/packets")
                .then(res => res.json())
                .then(data => setPackets(data))
                .catch(err => console.error(err));
        };

        fetchData();
        const interval = setInterval(fetchData, 1000);

        return () => clearInterval(interval);

    }, []);

    // Line chart data
    const limitedPackets = packets.slice(-50);

    const chartData = limitedPackets.map((p, index) => ({
        index: index + 1,
        size: p.size
    }));
    // Pie chart data
    const pieData = [
        { name: "TCP", value: tcpCount },
        { name: "UDP", value: udpCount }
    ];

    const COLORS = ["#00ffcc", "#ff4d4d"];

    return (
        <div style={{
            backgroundColor: "#121212",
            color: "white",
            minHeight: "100vh",
            padding: "20px",
            fontFamily: "Arial"
        }}>

            <h1>🚀 Network Analyzer Dashboard</h1>

            {/* STATS */}
            <div style={{ display: "flex", gap: "20px", marginBottom: "20px" }}>

                <div style={cardStyle}>
                    <h3>Total Packets</h3>
                    <p>{totalPackets}</p>
                </div>

                <div style={cardStyle}>
                    <h3>TCP Packets</h3>
                    <p>{tcpCount}</p>
                </div>

                <div style={cardStyle}>
                    <h3>UDP Packets</h3>
                    <p>{udpCount}</p>
                </div>

            </div>

            {/* GRAPH */}
            <h2>📈 Traffic Graph</h2>

            <LineChart width={800} height={300} data={chartData}>
                <CartesianGrid stroke="#444" />
                <XAxis dataKey="index" />
                <YAxis />
                <Tooltip />
                <Line type="monotone" dataKey="size" stroke="#00ffcc" />
            </LineChart>

            {/* PIE CHART */}
            <h2>📊 Protocol Distribution</h2>

            <PieChart width={400} height={300}>
                <Pie data={pieData} dataKey="value" outerRadius={100}>
                    {pieData.map((entry, index) => (
                        <Cell key={index} fill={COLORS[index % COLORS.length]} />
                    ))}
                </Pie>
                <Tooltip />
                <Legend />
            </PieChart>

            {/* SEARCH */}
            <input
                type="text"
                placeholder="Search by Source IP..."
                value={search}
                onChange={(e) => setSearch(e.target.value)}
                style={{
                    padding: "10px",
                    marginBottom: "20px",
                    width: "300px",
                    borderRadius: "5px"
                }}
            />

            {/* TABLE */}
            <table border="1" cellPadding="10" style={{
                width: "100%",
                borderCollapse: "collapse"
            }}>

                <thead>
                <tr>
                    <th>ID</th>
                    <th>Port</th>
                    <th>Source</th>
                    <th>Destination</th>
                    <th>Protocol</th>
                    <th>Size</th>
                </tr>
                </thead>

                <tbody>

                {packets
                    .filter(p => p.sourceIP?.includes(search))
                    .map(p => (

                        <tr
                            key={p.id}
                            style={{
                                backgroundColor: p.size > 1000 ? "#3b0000" : "transparent"
                            }}
                        >
                            <td>{p.id}</td>
                            <td>{p.port}</td>
                            <td>{p.sourceIP}</td>
                            <td>{p.destinationIP}</td>
                            <td>{p.protocol}</td>
                            <td>
                                {p.size} {p.size > 1000 && "⚠"}
                            </td>
                        </tr>

                    ))}

                </tbody>

            </table>

        </div>
    );
}

export default App;