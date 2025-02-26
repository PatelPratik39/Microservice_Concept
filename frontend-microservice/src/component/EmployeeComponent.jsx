import React, { Component } from "react";
import EmployeeService from "../service/EmployeeService";
import "bootstrap/dist/css/bootstrap.min.css"; // Import Bootstrap for styling

export default class EmployeeComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      employee: null,
      department: null,
      organization: null
    };
  }

  componentDidMount() {
    EmployeeService.getEmployee()
      .then((response) => {
        this.setState({
          employee: response.data.employee || {},
          department: response.data.department || {},
          organization: response.data.organization || {}
        });
      })
      .catch((error) => {
        console.error("Error fetching employee details:", error);
      });
  }

  render() {
    const { employee, department, organization } = this.state;

    return (
      <div className="container mt-4">
        <div className="row justify-content-center">
          <div className="col-lg-26">
            {/* Employee Details */}
            <div className="card shadow-sm mb-4">
              <div className="card-header text-center bg-primary text-white">
                <h4>Employee Details</h4>
              </div>
              <div className="card-body">
                {employee ? (
                  <>
                    <p>
                      <strong className="text-muted">First Name:</strong>{" "}
                      {employee.firstName || "N/A"}
                    </p>
                    <p>
                      <strong className="text-muted">Last Name:</strong>{" "}
                      {employee.lastName || "N/A"}
                    </p>
                    <p>
                      <strong className="text-muted">Email:</strong>{" "}
                      {employee.email || "N/A"}
                    </p>
                  </>
                ) : (
                  <p className="text-center text-danger">
                    Employee data not available
                  </p>
                )}
              </div>
            </div>

            {/* Department Details */}
            <div className="card shadow-sm mb-4">
              <div className="card-header text-center bg-success text-white">
                <h4>Department Details</h4>
              </div>
              <div className="card-body">
                {department ? (
                  <>
                    <p>
                      <strong className="text-muted">Name:</strong>{" "}
                      {department.departmentName || "N/A"}
                    </p>
                    <p>
                      <strong className="text-muted">Description:</strong>{" "}
                      {department.departmentDescription || "N/A"}
                    </p>
                    <p>
                      <strong className="text-muted">Code:</strong>{" "}
                      {department.departmentCode || "N/A"}
                    </p>
                  </>
                ) : (
                  <p className="text-center text-danger">
                    Department data not available
                  </p>
                )}
              </div>
            </div>

            {/* Organization Details */}
            <div className="card shadow-sm mb-4">
              <div className="card-header text-center bg-dark text-white">
                <h4>Organization Details</h4>
              </div>
              <div className="card-body">
                {organization ? (
                  <>
                    <p>
                      <strong className="text-muted">Organization Name:</strong>{" "}
                      {organization.organizationName || "N/A"}
                    </p>
                    <p>
                      <strong className="text-muted">Description:</strong>{" "}
                      {organization.organizationDescription || "N/A"}
                    </p>
                    <p>
                      <strong className="text-muted">Code:</strong>{" "}
                      {organization.organizationCode || "N/A"}
                    </p>
                  </>
                ) : (
                  <p className="text-center text-danger">
                    Organization data not available
                  </p>
                )}
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
