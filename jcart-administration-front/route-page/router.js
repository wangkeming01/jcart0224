const router = new VueRouter({
    routes: [
        // 动态路径参数 以冒号开头
        { path: '/product/search', component: ProductSearchRoutePage },
        { path: '/product/update/:productId', component: ProductUpdateRoutePage }
    ]
})