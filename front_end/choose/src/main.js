// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import Vuex from 'vuex'
import router from './router'
import axios from 'axios';
import ElementUI from 'element-ui'
import store from './store/store'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI);
Vue.use(Vuex)
Vue.config.productionTip = false;
Vue.prototype.$http = axios;
/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  template: '<App/>',
  components: { App }
})
