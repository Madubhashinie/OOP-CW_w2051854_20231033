import { useState, useEffect } from "react";
import axios from "axios";
import "./TicketStatus.css";

const TicketStatus = () => {
  const [totalTickets, setTotalTickets] = useState(0);
  const [maxCapacity, setMaxCapacity] = useState(0);
  const [error, setError] = useState("");

  /**
   * Fetches the current ticket pool status from the backend API
   */
  useEffect(() => {
    const fetchStatus = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/control/status");
        const { currentTickets, maxCapacity } = response.data;
        setTotalTickets(currentTickets);
        setMaxCapacity(maxCapacity);
      } catch (err) {
        console.error("Failed to fetch ticket status:", err);
        setError("Failed to load ticket status.");
      }
    };

    fetchStatus();

    //Refresh the ticket status every 5 seconds
    const interval = setInterval(fetchStatus, 5000);
    return () => clearInterval(interval);
  }, []);

  return (
    <div className="status">
      <h2>Ticket Pool Status</h2>
      {error && <p className="error">{error}</p>}
      <p>Remaining tickets in the current thread : {totalTickets}</p>
      <p>Maximum Ticket Capacity: {maxCapacity}</p>
    </div>
  );
};

export default TicketStatus;
