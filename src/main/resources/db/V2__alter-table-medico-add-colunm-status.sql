alter table medico add column status varchar(100) default 'ATIVO';
update medico set status = 'ATIVO' where status is null;