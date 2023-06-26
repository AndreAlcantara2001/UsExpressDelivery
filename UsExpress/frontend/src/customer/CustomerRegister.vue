<template>
    <div>
        <div class="container">
            <h2>Customer Registration Form</h2>
            <form @submit.prevent="handleSubmit">
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" id="name" name="name" v-model="customer.name" required>
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" v-model="customer.email" required>
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" v-model="customer.password" required>
                </div>

                <div class="form-group">
                    <label for="address">Address:</label>
                    <input type="text" id="address" name="address" v-model="customer.address" required>
                </div>

                <div class="form-group">
                    <label for="phoneNumber">Phone Number:</label>
                    <input type="tel" id="phoneNumber" name="phoneNumber" v-model="customer.phoneNumber" required>
                </div>

                <div class="form-group">
                    <input type="submit" value="Register">
                </div>
            </form>
        </div>
    </div>
</template>

<script>
import api from '@/utils/api';
export default {

    data() {
        return {

            customerId: '',

            customer: {
                name: '',
                email: '',
                password: '',
                address: '',
                phoneNumber: '',
            },
        }
    },

    methods: {
        async handleSubmit() {
            const respCustomer = await api.save('customer/save', this.customer);
            const customerData = await respCustomer.json();
            if (customerData) {
                console.log("Customer data: ", customerData);
            }

        this.customerId = customerData.customerId;

            //clear the form
            this.customer.name = '';
            this.customer.address = '';
            this.customer.email = '';
            this.customer.password = '';
            this.customer.phoneNumber = '';

            this.$router.push({ name: 'RequestDelivery', params: { id: this.customerId} });
        }
    }

}
</script>

<style></style>