import React, { useState } from "react";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import { experimentalStyled as styled } from "@mui/material/styles";
import "./Timesheet.css";
import logo from "../../../assets/Capture.png";
import axios from 'axios';

const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: "#043465",
  ...theme.typography.body2,
  padding: theme.spacing(2),
  color: "white",
  height: "240px",

}));

const Timesheet = ({ data, display }) => {

  const handleClick = (timesheetId, email) => {
    const f = timesheetId;
    const emailEmployee = email;
    const url = `http://localhost:8080/java/Manager/Timesheet/Approve/${f}`;
    axios.patch(url).then((result) => {


    }).catch((error) => {
      console.log(error)
    });

    const dataEmail = {
      to: emailEmployee,
      subject: "CONGRATULATIONS.......!!!!",
      message: "Your Timesheet is Approved"
    }

    const url1 = `http://localhost:8080/java/send`;
    axios.post(url1, dataEmail).then((result) => {

      alert("mail is succesfully send")
    }).catch((error) => {
      console.log(error)
    });
  };



  return (
    <div
      style={{
        backgroundImage: `url(${logo})`,
        backgroundRepeat: "no-repeat",
        backgroundPosition: "top center",
        backgroundSize: "400px",
        backgroundPositionY: "100px",
        backgroundPositionX: "100px",
      }}
    >
      <Box sx={{ display: "flex", alignItems: "flex-end", marginTop: "10px" }}>
        <span className="timesheet">{display}</span>{" "}

      </Box>

      <Box sx={{ flexGrow: 1 }} style={{ margin: "180px 20px 0px 20px" }}>
        <Grid
          container
          spacing={{ xs: 3, md: 3 }}
          columns={{ xs: 4, sm: 8, md: 12 }}
        >
          {data.map((item) => (
            <Grid item xs={2} sm={2} md={3} style={{ minWidth: '33%' }}>
              <Item>
                <h2>{item.timesheetId}</h2>
                <p>Name: {item.employee.employee_name}</p>
                <p>Start date: {item.startDate}</p>
                <p>End Date: {item.endDate}</p>
                <p>Worked Hours: {item.hours}</p>
                <p>Allocated Hours: {item.projectemployeemapping.allocation}</p>
                {(item.approval === 0) ? (<p>Status:Awaiting approval</p>) : (<p>Status:Approved</p>)}
                <button onClick={() => handleClick(item.timesheetId, item.employee.email)} type="button">Approve</button>
              </Item>
            </Grid>
          ))}
        </Grid>
      </Box>
    </div>
  );
};

export default Timesheet;
