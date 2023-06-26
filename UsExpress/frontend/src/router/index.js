import Vue from 'vue'
import VueRouter from 'vue-router'
import RiderRegister from '../rider/RiderRegister.vue'
import RiderHome from '../rider/RiderHome.vue'
import AdminHome from '../admin/AdminHome.vue'
import UsersData from '../admin/UsersData.vue'
import NearByRiders from '../customer/NearByRiders.vue'
import RequestDelivery from '../customer/RequestDelivery.vue'
import CustomerRegister from '../customer/CustomerRegister.vue'


Vue.use(VueRouter)

const routes = [
  {
    path: '/rider-register',
    name: 'RiderRegister',
    component: RiderRegister,
  },
  {
    path: '/rider-home/:id',
    name: 'RiderHome',
    component: RiderHome,
  },
  {
    path: '/admin/dashboard',
    name: 'AdminHome',
    component: AdminHome,
  },
  {
    path: '/admin/users',
    name: 'UsersData',
    component: UsersData,
  },
  {
    path: '/customer/register',
    name: 'CustomerRegister',
    component: CustomerRegister,
  },
  {
    path: '/customer/near-by-riders/:id',
    name: 'NearByRiders',
    component: NearByRiders,
  },
  {
    path : "customer/request-delivery/:id",
    name: "RequestDelivery",
    component: RequestDelivery,
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
