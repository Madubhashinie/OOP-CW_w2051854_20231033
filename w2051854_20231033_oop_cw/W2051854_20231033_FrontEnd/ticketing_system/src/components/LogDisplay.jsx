import PropTypes from "prop-types";
import "./LogDisplay.css";

const LogDisplay = ({ logs }) => {
  return (
    <div className="log_display">
      <h2>Transaction Logs</h2>

      {logs.length === 0 ? (
        <p className="no-logs">No transactions available</p>
      ) : (
        <table className="logs-table">
          <thead>
            <tr>
              <th>Transaction ID</th>
              <th>User</th>
              <th>Action</th>
              <th>Date/Time</th>
            </tr>
          </thead>
          <tbody>
            {logs.map((log) => (
              <tr key={log.transactionID}>
                <td>{log.transactionID}</td>
                <td>{log.user}</td>
                <td>{log.action}</td>
                <td>{new Date(log.dateTime).toLocaleString()}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

// PropTypes Validation
LogDisplay.propTypes = {
  logs: PropTypes.arrayOf(
    PropTypes.shape({
      transactionID: PropTypes.number.isRequired,
      user: PropTypes.string.isRequired,
      action: PropTypes.string.isRequired,
      dateTime: PropTypes.string.isRequired,
    })
  ).isRequired,
};

export default LogDisplay;


