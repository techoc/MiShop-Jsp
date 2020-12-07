/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/4/14 0:54:04                            */
/*==============================================================*/


drop table if exists address;

drop table if exists cart;

drop table if exists item;

drop table if exists orders;

drop table if exists product;

drop table if exists type;

drop table if exists user;

/*==============================================================*/
/* Table: address                                               */
/*==============================================================*/
create table address
(
   a_id                 int not null auto_increment comment '地址实体的唯一主键列',
   u_id                 int comment '用户实体的主键属性',
   a_name               varchar(30) comment '地址的收件人',
   a_phone              varchar(14) comment '收件人电话',
   a_detail             varchar(200) comment '收货人详细地址',
   a_state              int comment '是否是默认地址 0 不是 1是默认地址',
   primary key (a_id)
);

alter table address comment '用户个人中心的地址实体，用于存储地址信息';

/*==============================================================*/
/* Table: cart                                                  */
/*==============================================================*/
create table cart
(
   c_id                 int not null auto_increment comment '购物车的唯一标识',
   u_id                 int comment '用户实体的主键属性',
   p_id                 int comment '商品的唯一主键',
   c_count              decimal(12,2) comment '购物车小计',
   c_num                int comment '购物车商品数量',
   primary key (c_id)
);

alter table cart comment '购物车实体';

/*==============================================================*/
/* Table: item                                                  */
/*==============================================================*/
create table item
(
   i_id                 int not null auto_increment comment '订单项的唯一标识',
   o_id                 varchar(64) comment '订单编号是字符串类型但是也是唯一标识',
   p_id                 int comment '商品的唯一主键',
   i_count              decimal(12,2) comment '订单项的小计',
   i_num                int comment '订单项的数量',
   primary key (i_id)
);

alter table item comment '订单内部的具体商品展示项';

/*==============================================================*/
/* Table: orders                                                */
/*==============================================================*/
create table orders
(
   o_id                 varchar(64) not null comment '订单编号是字符串类型但是也是唯一标识',
   u_id                 int comment '用户实体的主键属性',
   a_id                 int comment '地址实体的唯一主键列',
   o_count              decimal(12,2) comment '订单的总金额',
   o_time               datetime comment '订单的详细时间',
   o_state              int comment '订单状态 0 未付款，1已经付款未发货 2发货待收货 3 收货待评价 4订单完成 5 退货状态',
   primary key (o_id)
);

alter table orders comment '订单模块的订单实体';

/*==============================================================*/
/* Table: product                                               */
/*==============================================================*/
create table product
(
   p_id                 int not null auto_increment comment '商品的唯一主键',
   t_id                 int comment '类别的主键id',
   p_name               varchar(50) comment '商品的名称',
   p_time               date comment '商品的上市时间',
   p_image              varchar(100) comment '商品图片的路径',
   p_price              decimal(12,2) comment '商品的价格',
   p_state              int comment '商品的热门指数',
   p_info               varchar(200) comment '商品的描述',
   primary key (p_id)
);

alter table product comment '商品模块的商品实体';

/*==============================================================*/
/* Table: type                                                  */
/*==============================================================*/
create table type
(
   t_id                 int not null auto_increment comment '类别的主键id',
   t_name               varchar(20) comment '类别的名称',
   t_info               varchar(200) comment '类别的描述',
   primary key (t_id)
);

alter table type comment '商品模块的类别实体';

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   u_id                 int not null auto_increment comment '用户实体的主键属性',
   u_name               varchar(20) not null comment '用户账号',
   u_password           varchar(64) not null comment '用户密码',
   u_email              varchar(50) not null comment '用户的邮箱！用于激活使用！',
   u_sex                varchar(4) comment '用户性别！',
   u_status             int comment '用户的激活状态 0 未激活 1 激活',
   u_code               varchar(64) comment '邮件激活码',
   u_role               int comment '用户 0 管理员 1',
   primary key (u_id)
);

alter table user comment '用户模块的用户实体';

alter table address add constraint FK_u_a_fk foreign key (u_id)
      references user (u_id) on delete restrict on update restrict;

alter table cart add constraint FK_p_c_fk foreign key (p_id)
      references product (p_id) on delete restrict on update restrict;

alter table cart add constraint FK_u_c_fk foreign key (u_id)
      references user (u_id) on delete restrict on update restrict;

alter table item add constraint FK_o_i_fk foreign key (o_id)
      references orders (o_id) on delete restrict on update restrict;

alter table item add constraint FK_p_i_fk foreign key (p_id)
      references product (p_id) on delete restrict on update restrict;

alter table orders add constraint FK_a_o_fk foreign key (a_id)
      references address (a_id) on delete restrict on update restrict;

alter table orders add constraint FK_u_o_fk foreign key (u_id)
      references user (u_id) on delete restrict on update restrict;

alter table product add constraint FK_t_p_fk foreign key (t_id)
      references type (t_id) on delete restrict on update restrict;

