import { useState, useEffect, useCallback } from "react";


import ConfigurationForm from "./components/Configuration"; 
import TicketStatus from "./components/TicketStatus";
import ControlPanel from "./components/ControlPanel";
import LogDisplay from "./components/LogDisplay";

import axios from "axios";
import "./App.css";

function App() {
  const API_BASE_URL = "http://localhost:8080/api";
  const CONFIG_API = `${API_BASE_URL}/configuration`;
  const TICKET_API = `${API_BASE_URL}/tickets`;
  const CONTROL_API = `${API_BASE_URL}/control`;
  const TRANSACTION_API = `${API_BASE_URL}/transactions`;


  
  const [tickets, setTickets] = useState([]);
  const [isRunning, setIsRunning] = useState(false);
  const [config, setConfig] = useState(null);
  const [transactions, setTransactions] = useState([]);
 

  /**
   * Fetch configuration from API
   */
  const fetchConfig = useCallback(async () => {
    try {
      const response = await axios.get(CONFIG_API);
      setConfig(response.data);
    } catch (error) {
      console.error("Failed to fetch configuration:", error);
    }
  }, [CONFIG_API]);

  /**
   * Fetches transactions from the API
   */
  const fetchTransactions = useCallback(async () => {
    try {
      const response = await axios.get(TRANSACTION_API);
      console.log("Fetched Transactions:", response.data); 
  
      // Ensure response is an array before setting state
      if (Array.isArray(response.data)) {
        setTransactions(response.data);
      } else {
        console.error("Unexpected response format:", response.data);
      }
    } catch (error) {
      console.error("Failed to fetch transactions:", error);
    }
  }, [TRANSACTION_API]);



  /**
   * Fetch tickets from API
   */
  const refreshTickets = async () => {
    try {
      const response = await axios.get(TICKET_API);
      setTickets(response.data);
      
    } catch (error) {
      console.error("Failed to fetch tickets:", error);
    
    }
  };

  useEffect(() => {
    fetchConfig();
    fetchTransactions();
  }, [fetchConfig, fetchTransactions]);

  

  /**
  * start Ticket Pool
  */
  const start = async () => {
    try {
      await axios.post(`${CONTROL_API}/start`);
      setIsRunning(true);
      refreshTickets();
      fetchTransactions();
    } catch (error) {
      console.error("Failed to start system:", error);
    }
  };

  /**
   * Stop Ticket Pool
   */
  const stop = async () => {
    try {
      await axios.post(`${CONTROL_API}/stop`);
      setIsRunning(false);
    } catch (error) {
      console.error("Failed to stop system:", error);
    }
  };

  /**
   * Reset Ticket Pool
   */
  const reset= async () => {
    try {
      await axios.post(`${CONTROL_API}/reset`);
      setTickets([]);
      setIsRunning(false);
    } catch (error) {
      console.error("Failed to reset system:", error);
    }
  };

  
  return (
    <div className="app">
      <h1>Real-Time Ticketing System</h1>
      <img
        src="https://www.itarian.com/images/ticketing/what-is-archtics-ticketing-system.png"
        alt="Ticketing System"
        className="header-image"
      />

      <ConfigurationForm
        config={config}
        CONFIG_API={CONFIG_API}
        isRunning={isRunning}
      />

      <ControlPanel
        onSystemStart={start}
        onSystemStop={stop}
        onSystemReset={reset}
      />

      <TicketStatus tickets={tickets} maxCapacity={config?.maxTicketCapacity} />
      
      
      <LogDisplay logs={transactions} />
      
    </div>
  );
}

export default App;

