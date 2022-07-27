import Vue from 'vue'
import App from './App.vue'
import vueRouter from 'vue-router'
import router from './router'
import element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.config.productionTip = false
Vue.use(vueRouter)
Vue.use(element)

new Vue({
  render: h => h(App),
  router: router
}).$mount('#app')
