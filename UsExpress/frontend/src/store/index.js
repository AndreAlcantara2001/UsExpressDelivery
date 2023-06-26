import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    ridersNearCustomer: {}
  },
  getters: {
    getNearRiders: (state) => (customerId) => state.ridersNearCustomer[customerId] || []
  
  },
  mutations: {
    setNearRiders(state, payload) {
      const { customerId, nearRiders } = payload;
      Vue.set(state.ridersNearCustomer, customerId, nearRiders);
    }
  },
  actions: {
    updateNearRiders({ commit }, payload) {
      commit('setNearRiders', payload);
    }
  },
  modules: {
  }
})
