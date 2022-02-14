import Vue from 'vue'
import App from './App.vue'
import { router } from './router'
import store from './store'
import 'normalize.css/normalize.css'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import '@/styles/index.scss' // global css
import './icons' // icon
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
Vue.use(Element, {
  size: 'medium' // set element-ui default size
})
// 阻止启动生产消息
Vue.config.productionTip = false
// 进度环显示隐藏
NProgress.configure({ showSpinner: false }) // NProgress Configuration
// 它是一个全局的before 钩子函数， （before each）意思是在 每次每一个路由改变的时候都得执行一遍。
// 它的三个参数：
// to: (Route路由对象)  即将要进入的目标 路由对象       to对象下面的属性： path   params  query   hash   fullPath    matched   name    meta（在matched下，但是本例可以直接用）
// from: (Route路由对象)  当前导航正要离开的路由
// next: (Function函数)   一定要调用该方法来 resolve 这个钩子。  调用方法：next(参数或者空)   ***必须调用
// next(无参数的时候):  进行管道中的下一个钩子，如果走到最后一个钩子函数，那么  导航的状态就是 confirmed （确认的）
// next('/') 或者 next({ path: '/' }): 跳转到一个不同的地址。当前的导航被中断，然后进行一个新的导航。
router.beforeEach(async (to, from, next) => {
  // start progress bar
  NProgress.start()
  if (to.meta.title !== undefined) {
    document.title = to.meta.title
  } else {
    document.title = '\u200E'
  }

  if (to.meta.bodyBackground !== undefined) {
    document.querySelector('body').setAttribute('style', 'background: ' + to.meta.bodyBackground)
  } else {
    document.querySelector('body').removeAttribute('style')
  }
  console.log(to.fullPath)
  console.log(to.path)
  if (to.path) {
    // eslint-disable-next-line no-undef
    _hmt.push(['_trackPageview', '/#' + to.fullPath])
  }
  next()
})

router.afterEach((to, from, next) => {
  // finish progress bar
  NProgress.done()
})

Vue.prototype.$$router = router
// render函数是渲染一个视图，然后提供给el挂载，如果没有render那页面什么都不会出来
// vue.2.0的渲染过程：
// 1.首先需要了解这是 es 6 的语法，表示 Vue 实例选项对象的 render 方法作为一个函数，接受传入的参数 h 函数，返回 h(App) 的函数调用结果。
// 2.其次，Vue 在创建 Vue 实例时，通过调用 render 方法来渲染实例的 DOM 树。
// 3.最后，Vue 在调用 render 方法时，会传入一个 createElement 函数作为参数，也就是这里的 h 的实参是 createElement 函数，然后 createElement 会以 APP 为参数进行调用，关于 createElement 函数的参数说明参见：Element-Arguments
// 结合一下官方文档的代码便可以很清晰的了解Vue2.0 render:h => h(App)的渲染过程
new Vue({
  router: router,
  store: store,
  render: h => h(App)
}).$mount('#app')
