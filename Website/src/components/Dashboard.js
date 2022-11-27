import React, { useState } from "react";
import { Card, Button, Alert } from "react-bootstrap";
import { useAuth } from "../contexts/AuthContext";
import { Link, useHistory } from "react-router-dom";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import "./style.css";
import UploadForm from "./UploadForm";

export default function Dashboard() {
  const [error, setError] = useState("");
  const { currentUser, logout } = useAuth();
  const history = useHistory();

  async function handleLogout() {
    setError("");

    try {
      await logout();
      history.push("/login");
    } catch {
      setError("Failed to log out");
    }
  }

  return (
    <>
      <div>
        <Navbar fixed="top" bg="dark" variant="dark">
          <Container>
            <Navbar.Brand href="#home">4rtist</Navbar.Brand>
            <Nav className="me-auto">
              <Nav.Link href="#home">Profile</Nav.Link>
            </Nav>
            <Navbar.Collapse className="justify-content-end">
              <Navbar.Text>
                <strong>Signed in as:</strong>{" "}
                <Link to="/update-profile">{currentUser.email}</Link>
              </Navbar.Text>
              <Button
                variant="outline-light"
                onClick={handleLogout}
                style={{ marginLeft: "10px" }}
              >
                Log Out
              </Button>
            </Navbar.Collapse>
          </Container>
        </Navbar>
        <div className="w-10 h-20">
          <UploadForm />
        </div>
      </div>
    </>
  );
}
