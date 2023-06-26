<template>
    <div class="container">
      <h1>Rider Registration</h1>
      <form @submit.prevent="registerRider">

        <div class="form-group">
          <label for="name">Name:</label>
          <input v-model="rider.name" type="text" class="form-control" id="name" required>
        </div>
        
        <button type="submit" class="btn btn-primary">Register</button>
      </form>
    </div>
  </template>
  
  <script>

import api from '../utils/api.js'
  export default {
    data() {
      return {
        riderId: '',
        rider:{
        name: '',
        latitude: '',
        longitude: ''
        }
      };
    },
    methods: {
      async registerRider() {

        //send to backend
        const respRider = await api.save("rider/save",this.rider);
        const respData = await respRider.json();
        if(respData){
            console.log(respData);
            console.log("Rider id : ",respData.id);
        }

        this.riderId = respData.id;

        console.log('Rider registered:', {
          riderId: this.rider.id,
          name: this.rider.name,
          latitude: this.rider.latitude,
          longitude: this.rider.longitude
        });
  
        // Clear the form
        this.id = '';
        this.name = '';
        this.latitude = '';
        this.longitude = '';

        this.$router.push({ name: 'RiderHome', params: {id: respData.id}});

      }
    }
  };
  </script>
  
  <style scoped>
  .container {
    max-width: 400px;
    margin: 0 auto;
    padding: 40px;
    background-color: #f8f9fa;
    border: 1px solid #dcdcdc;
    border-radius: 5px;
  }
  
  h1 {
    text-align: center;
  }
  
  .form-group {
    margin-bottom: 20px;
  }
  
  label {
    font-weight: bold;
  }
  
  input[type="text"] {
    width: 100%;
    padding: 10px;
    border: 1px solid #dcdcdc;
    border-radius: 5px;
  }
  
  button[type="submit"] {
    width: 100%;
    padding: 10px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  </style>
  