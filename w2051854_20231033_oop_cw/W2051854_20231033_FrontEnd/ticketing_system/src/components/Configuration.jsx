import { useState, useEffect } from "react";
import PropTypes from "prop-types";
import "./Configuration.css";
import axios from "axios";

const ConfigurationForm = ({ config, CONFIG_API, isRunning }) => {

  const [totalTickets, setTotalTickets] = useState("");
  const [ticketReleaseRate, setTicketReleaseRate] = useState("");
  const [customerRetrievalRate, setCustomerRetrievalRate] = useState("");
  const [maxTicketCapacity, setMaxTicketCapacity] = useState("");
  const [formError, setError] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  useEffect(() => {
    if (config) {
      setTotalTickets(config.totalTickets || "");
      setTicketReleaseRate(config.ticketReleaseRate || "");
      setCustomerRetrievalRate(config.customerRetrievalRate || "");
      setMaxTicketCapacity(config.maxTicketCapacity || "");
    }
  }, [config]);

  const validateInputs = async (e) => {
    e.preventDefault();

    if (!CONFIG_API) {
      console.error("API URL is missing!");
      setError("API endpoint is not configured.");
      return;
    }

    // Validation
    if (!totalTickets || totalTickets < 1 || totalTickets > 100) {
      setError("Total Tickets must be between 1 and 100.");
      return;
    }

    if (!maxTicketCapacity || maxTicketCapacity < totalTickets || maxTicketCapacity > 100) {
      setError(`Max Ticket Capacity must be between ${totalTickets} and 100.`);
      return;
    }

    if (!ticketReleaseRate || ticketReleaseRate < 1 || ticketReleaseRate > 10000) {
      setError("Ticket Release Rate must be between 1 and 10000 ms.");
      return;
    }

    if (!customerRetrievalRate || customerRetrievalRate < 1 || customerRetrievalRate > 10000) {
      setError("Customer Retrieval Rate must be between 1 and 10000 ms.");
      return;
    }

    setError("");

    // Send to Backend
    try {
      const response = await axios.post(CONFIG_API, {
        totalTickets: totalTickets === 0 ? null : totalTickets,
        maxTicketCapacity,
        ticketReleaseRate,
        customerRetrievalRate,
      });
      setSuccessMessage("Configuration saved successfully.");
      console.log(response.data);
    } catch (err) {
      console.error(err);
      setError("Failed to update configuration.");
    }
  };

  const resetTotalTickets = () => {
    if (totalTickets === 0) {
      const newTotalTickets = prompt("Enter total tickets (1 to 100):", "1");
      if (newTotalTickets && newTotalTickets >= 1 && newTotalTickets <= 100) {
        setTotalTickets(newTotalTickets);
      } else {
        alert("Invalid input. Total Tickets must be between 1 and 100.");
      }
    }
  };

  useEffect(() => {
    if (totalTickets === 0 && !isRunning) {
      resetTotalTickets();
    }
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [totalTickets, isRunning]);

  return (
    <form onSubmit={validateInputs} className="config-form">
      <h2>Event Ticket Controls</h2>

      <label>
        Total Tickets:
        <input
          type="number"
          value={totalTickets}
          onChange={(e) => setTotalTickets(e.target.value)}
          disabled={isRunning}
        />
      </label>

      <label>
        Ticket Release Rate:
        <input
          type="number"
          value={ticketReleaseRate}
          onChange={(e) => setTicketReleaseRate(e.target.value)}
          disabled={isRunning}
        />
      </label>

      <label>
        Customer Retrieval Rate:
        <input
          type="number"
          value={customerRetrievalRate}
          onChange={(e) => setCustomerRetrievalRate(e.target.value)}
          disabled={isRunning}
        />
      </label>

      <label>
        Maximum Ticket Capacity:
        <input
          type="number"
          value={maxTicketCapacity}
          onChange={(e) => setMaxTicketCapacity(e.target.value)}
          disabled={isRunning}
        />
      </label>

      <button type="submit" disabled={isRunning}>
        Confirm Configuration Changes
      </button>

      {formError && <p className="error">{formError}</p>}
      {successMessage && <p className="success">{successMessage}</p>}
    </form>
  );
};

// PropTypes Validation
ConfigurationForm.propTypes = {
  config: PropTypes.object,
  CONFIG_API: PropTypes.string.isRequired,
  isRunning: PropTypes.bool.isRequired,
};

export default ConfigurationForm;







