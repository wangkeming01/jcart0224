const router = new VueRouter({
    routes: [
        // 动态路径参数 以冒号开头
        { path: '/product/search', component: ProductSearchRoutePage },
        { path: '/product/update/:productId', component: ProductUpdateRoutePage },
        { path: '/customer/search', component: CustomerSearchRoutePage },
        { path: '/order/search', component: OrderSearchRoutePage },
        { path: '/return/search', component: RetuenSearchRoutePage },
        { path: '/administrator/index', component: AdministratorIndexRoutePage },
        { path: '/administrator/create', component: AdministratorCreateRoutePage },
        { path: '/administrator/update/:administratorId', component: AdministratorUpdateRoutePage }
    ]
})