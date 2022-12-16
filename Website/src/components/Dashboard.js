import React, { useState, useEffect } from "react";
import { Card, Button, Alert } from "react-bootstrap";
import { useAuth } from "../contexts/AuthContext";
import { Link, useHistory } from "react-router-dom";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import "./style.css";
import { storage } from "../firebase.js";
import { ref, uploadBytes, listAll, getDownloadURL } from "firebase/storage";
import { v4 } from "uuid";

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

  const [imageUpload, setImageUpload] = useState(null);
  const [imageList, setImageList] = useState([]);

  const imageListRef = ref(storage, "images/");
  const uploadImage = () => {
    if (imageUpload == null) return;
    const imageRef = ref(storage, `images/${imageUpload.name + v4()}`);
    uploadBytes(imageRef, imageUpload).then(() => {
      alert("Image Uploaded. Refresh the Page");
    });
  };
  useEffect(() => {
    listAll(imageListRef).then((response) => {
      response.items.forEach((item) => {
        getDownloadURL(item).then((url) => {
          setImageList((prev) => [...prev, url]);
        });
      });
    });
  }, []);

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
        <div className="main-content-box">
          <div className="upload-box">
            <p>Upload your Artwork Here</p>
            <input
              type="file"
              onChange={(event) => {
                setImageUpload(event.target.files[0]);
              }}
            />
            <br />
            <br />
            <button onClick={uploadImage}>Upload Image</button>
          </div>

          <div className="photobox">
            {imageList.map((url) => {
              return <img className="imageDisplay" src={url} />;
            })}
          </div>
        </div>
      </div>
    </>
  );
}
