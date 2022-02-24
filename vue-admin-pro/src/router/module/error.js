
// 错误页
export const error = [
  {
    path: '/404',
    hidden: true,
    component: () => import('@/views/error/404')
  },
  {
    path: '/401',
    hidden: true,
    component: () => import('@/views/error/401')
  }
]
