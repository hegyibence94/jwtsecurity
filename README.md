# jwtsecurity

1. Set up your own database at MySQL, connect it to the project in the application.properties.
2. Build your project with create-drop strategy.
3. Stop the project.
4. Create the roles with the my SQL command below:
	INSERT INTO NAME_OF_YOUR_DATABASE.roles(name) VALUES('ROLE_USER');

	INSERT INTO NAME_OF_YOUR_DATABASE.roles(name) VALUES('ROLE_APPRENTICE');

	INSERT INTO NAME_OF_YOUR_DATABASE.roles(name) VALUES('ROLE_PARTNER');

	INSERT INTO NAME_OF_YOUR_DATABASE.roles(name) VALUES('ROLE_MENTOR');
	INSERT INTO NAME_OF_YOUR_DATABASE.roles(name) VALUES('ROLE_ADMIN');	


5. Registrate a new user at the /registrate endpoint.
6. Give him admin role with the following SQL command:
	INSERT INTO NAME_OF_YOUR_DATABASE.user_roles(client_id, client_role_id) VALUES(1,5);
	
7. Registrate more clients, handle them with your admin account.