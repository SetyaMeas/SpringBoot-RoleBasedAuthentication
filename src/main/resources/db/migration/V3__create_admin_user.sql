-- password: adminadmin

INSERT INTO tbl_user (name, email, pwd)
VALUES ('ADMIN', 'admin@admin.com', '$2a$10$akfMmQcZxHc4TlWwqbUOp.DWhhSWn/N8jGqe7TXnCixzmmy4F0WFm');

insert into tbl_user_role (user_id, role_id)
values (1, 1), (1, 2);