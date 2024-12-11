import PropTypes from "prop-types";
import './ControlPanel.css';
import axios from "axios";
import { useState } from "react";

const ControlPanel = ({ onSystemStart, onSystemStop, onSystemReset }) => {
  const API_BASE_URL = "http://localhost:8080/api";
  const CONTROL_API = `${API_BASE_URL}/control`;

  const [isRunning, setIsRunning] = useState(false);

  /**
   * Starts the system by sending a request to the backend API
   */
  const start = async () => {
    try {
      const response = await axios.post(`${CONTROL_API}/start`);
      console.log(response.data);
      setIsRunning(true);
      if (onSystemStart) onSystemStart();
    } catch (error) {
      console.error("Failed to start the system:", error);
      if (onSystemStart) onSystemStart(false);
    }
  };

  /**
   * Stops the system by sending a request to the backend API
   */
  const stop = async () => {
    try {
      const response = await axios.post(`${CONTROL_API}/stop`);
      console.log(response.data);
      setIsRunning(false);
      if (onSystemStop) onSystemStop();
    } catch (error) {
      console.error("Failed to stop the system:", error);
      if (onSystemStop) onSystemStop(false);
    }
  };

  /**
   * Resets the system by sending a request to the backend API
   */
  const reset = async () => {
    try {
      const response = await axios.post(`${CONTROL_API}/reset`);
      console.log(response.data);
      setIsRunning(false);
      if (onSystemReset) onSystemReset();
    } catch (error) {
      console.error("Failed to reset the system:", error);
      if (onSystemReset) onSystemReset(false);
    }
  };

  return (
    <div className="control-panel">
      <h2>Control Panel</h2>
      <div className="buttons">
        <button onClick={start} disabled={isRunning}>
          Start System
        </button>
        <button onClick={stop} disabled={!isRunning}>
          Stop System
        </button>
        <button onClick={reset}>Reset</button>
      </div>
    </div>
  );
};

/**
 * PropTypes validation for component properties
 */
ControlPanel.propTypes = {
  onSystemStart: PropTypes.func,
  onSystemStop: PropTypes.func,
  onSystemReset: PropTypes.func,
};

export default ControlPanel;
