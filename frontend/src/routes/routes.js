import Login from '@/services/auth/Login'
import Signup from '@/services/auth/Signup'
import Logviewer from '@/services/logviewer/main'
import Coder from '@/services/coder/main'
import McMockup from '@/services/mcmockup/main'
import Monitor from '@/services/monitor/main'

import MccLayout from '@/components/Layout/MccLayout'
const routes = [
  {
    path: '*',
    redirect: '/auth/login'
  },
  {
    path: '/',
    redirect: '/auth/login'
  },
  {
    path: '/auth/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/auth/signup',
    name: 'Signup',
    component: Signup
  },
  {
    path: '/mcc',
    component: MccLayout,
    children: [
      {
        path: 'logviewer',
        name: 'Logviewer',
        component: Logviewer,
        meta: {
          requiresAuth: true
        }
      },
      {
        path: 'mockup',
        name: 'McMockup',
        component: McMockup,
        meta: {
          requiresAuth: true
        }
      },
      {
        path: 'coder',
        name: 'Coder',
        component: Coder,
        meta: {
          requiresAuth: true
        }
      },
      {
        path: 'monitor',
        name: 'Monitor',
        component: Monitor,
        meta: {
          requiresAuth: true
        }
      }
    ]
  }
]

/**
 * Asynchronously load view (Webpack Lazy loading compatible)
 * The specified component must be inside the Views folder
 * @param  {string} name  the filename (basename) of the view to load.
function view(name) {
   var res= require('../components/Dashboard/Views/' + name + '.vue');
   return res;
};**/

export default routes
