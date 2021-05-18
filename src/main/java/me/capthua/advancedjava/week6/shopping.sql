create table tb_goods
(
    id          varchar(32)  null,
    name        varchar(64)  null,
    description varchar(512) null,
    create_time datetime     null,
    modify_time datetime     null,
    quantity    int          null
);

create table tb_order
(
    id          varchar(32) null,
    create_time datetime    null,
    modify_time datetime    null,
    total_cost  float       null comment '商品总额',
    payment_id  varchar(32) null comment '支付详情id',
    delivery_id varchar(32) null comment '配送详情id',
    state       tinyint     null comment '订单状态',
    user_id     varchar(32) null
);

create table tb_order_goods
(
    id       varchar(32) null,
    order_id varchar(32) null,
    goods_id varchar(32) null,
    count    int         null
);

create table tb_user
(
    id           varchar(32)  null,
    username     varchar(32)  null,
    password     varchar(64)  null,
    full_name    varchar(64)  null,
    nickname     varchar(64)  null,
    email        varchar(128) null,
    phone_number varchar(16)  null,
    create_time  datetime     null,
    update_time  datetime     null,
    active       tinyint(1)   null
);


