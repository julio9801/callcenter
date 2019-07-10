insert into a_menu values(30,'Usuarios',null,null);
insert into a_menu values(31,'Administración','/pages/usuarios/listaUsuarios.xhtml',30);
insert into r_menu_rol values(30,1);
insert into r_menu_rol values(31,1);
alter table k_usuario  ALTER column id  set DEFAULT nextval('ic_usuarios_idusuario_seq'::regclass);
alter table k_usuario drop column nombre_usuario;
