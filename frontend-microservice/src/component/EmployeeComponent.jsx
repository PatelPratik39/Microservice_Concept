import React, { Component } from "react";
import EmployeeService from "../service/EmployeeService";

export default class EmployeeComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      employee: {},
      department: {},
      organization: {}
    };
  }

  componentDidMount() {
    EmployeeService.getEmployee().then((response) => {
      this.setState({ employee: response.data.employee });
      this.setState({ department: response.data.department });
      this.setState({ organization: response.data.organization });

      console.log(this.state.employee);
      console.log(this.state.department);
      console.log(this.state.organization);
    });
  }
  render() {
    return (
      <>
        <div className="container mt-4">
          <div className="row justify-content-center">
            <div className="col-md-8">
              {/* Employee Details */}
              <div className="card shadow-sm mb-4">
                <div className="card-header text-center bg-primary text-white">
                  <h4>Employee Details</h4>
                </div>
                <div className="card-body">
                  <p>
                    <strong className="text-muted">First Name:</strong>{" "}
                    {this.state.employee.firstName}
                  </p>
                  <p>
                    <strong className="text-muted">Last Name:</strong>{" "}
                    {this.state.employee.lastName}
                  </p>
                  <p>
                    <strong className="text-muted">Email:</strong>{" "}
                    {this.state.employee.email}
                  </p>
                </div>
              </div>

              {/* Department Details */}
              <div className="card shadow-sm mb-4">
                <div className="card-header text-center bg-success text-white">
                  <h4>Department Details</h4>
                </div>
                <div className="card-body">
                  <p>
                    <strong className="text-muted">Name:</strong>{" "}
                    {this.state.department.departmentName}
                  </p>
                  <p>
                    <strong className="text-muted">Description:</strong>{" "}
                    {this.state.department.departmentDescription}
                  </p>
                  <p>
                    <strong className="text-muted">Code:</strong>{" "}
                    {this.state.department.departmentCode}
                  </p>
                </div>
              </div>

              {/* Organization Details */}
              <div className="card shadow-sm mb-4">
                <div className="card-header text-center bg-dark text-white">
                  <h4>Organization Details</h4>
                </div>
                <div className="card-body">
                  <p>
                    <strong className="text-muted">Organization Name:</strong>{" "}
                    {this.state.organization.organizationName}
                  </p>
                  <p>
                    <strong className="text-muted">Description:</strong>{" "}
                    {this.state.organization.organizationDescription}
                  </p>
                  <p>
                    <strong className="text-muted">Code:</strong>{" "}
                    {this.state.organization.organizationCode}
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
        
      </>
    );
  }
}
