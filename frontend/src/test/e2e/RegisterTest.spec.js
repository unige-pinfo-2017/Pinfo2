describe("Register Tests",function(){

	it("Url Register", function(){
		browser.get('http://localhost/register');
		browser.driver.sleep(10000).then(function(){
			expect(browser.getCurrentUrl()).toEqual('http://localhost/register');
		});		
	});

	it("Verification 4 inputs", function(){
		var countInput=element.all(by.id("input")).count();
		expect(countInput,4);		
	});

	it("Nom First Name", function(){
		var countFirstName=element.all(by.name("First Name")).count();
		expect(countFirstName,1);		
	});

	it("Nom Last Name", function(){
		var countLastName=element.all(by.name("Last Name")).count();
		expect(countLastName,1);		
	});

	it("Nom Username", function(){
		var countUsername=element.all(by.name("Username")).count();
		expect(countUsername,1);
	});

	it("Nom Password", function(){
		var countPassword=element.all(by.name("Password")).count();
		expect(countPassword,1);		
	});

	it("Compte le nombre de boutons", function(){
		expect(element.all(by.id("button")).count(),2);	
	});

	it("Verifie si il y a un bouton Register", function(){
		expect(element.all(by.buttonText("Register")).count(),1);	
	});

	it("Verifie si il y a un bouton Cancel", function(){
		expect(element.all(by.linkText("Cancel")).count(),1);
	});
	
	it("Essai Register sans FirstName", function(){
		browser.get('http://localhost/register');
		browser.driver.sleep(10000).then(function(){
			var inputLastName=element(by.css('[name="lastName"]'))
			inputLastName.sendKeys("LastName");
			var inputUsername=element(by.css('[name="username"]'))
			inputUsername.sendKeys("Username");
			var inputPassword=element(by.css('[name="password"]'))
			inputPassword.sendKeys("Password");
			element.all(by.buttonText("Register")).click();
			browser.driver.sleep(1000).then(function(){
				expect(element.all(by.className("help-block")).getText()).toEqual(['First Name is required']);
			});		
		});
	});

	it("Essai Register sans LastName", function(){
		browser.get('http://localhost/register');
		browser.driver.sleep(10000).then(function(){
			var inputFirstName=element(by.css('[name="firstName"]'))
			inputFirstName.sendKeys("FirstName");
			var inputUsername=element(by.css('[name="username"]'))
			inputUsername.sendKeys("Username");
			var inputPassword=element(by.css('[name="password"]'))
			inputPassword.sendKeys("Password");
			element.all(by.buttonText("Register")).click();
			browser.driver.sleep(1000).then(function(){
				expect(element.all(by.className("help-block")).getText()).toEqual(['Last Name is required']);
			});		
		});
	});

	it("Essai Register sans UserName", function(){
		browser.get('http://localhost/register');
		browser.driver.sleep(10000).then(function(){
			var inputFirstName=element(by.css('[name="firstName"]'))
			inputFirstName.sendKeys("FirstName");
			var inputLastname=element(by.css('[name="lastName"]'))
			inputLastname.sendKeys("lastName");
			var inputPassword=element(by.css('[name="password"]'))
			inputPassword.sendKeys("Password");
			element.all(by.buttonText("Register")).click();
			browser.driver.sleep(1000).then(function(){
				expect(element.all(by.className("help-block")).getText()).toEqual(['Username is required']);
			});		
		});
	});

	it("Essai Register sans Password", function(){
		browser.get('http://localhost/register');
		browser.driver.sleep(10000).then(function(){
			var inputFirstName=element(by.css('[name="firstName"]'))
			inputFirstName.sendKeys("FirstName");
			var inputLastname=element(by.css('[name="lastName"]'))
			inputLastname.sendKeys("lastName");
			var inputUsername=element(by.css('[name="username"]'))
			inputUsername.sendKeys("Username");
			element.all(by.buttonText("Register")).click();
			browser.driver.sleep(1000).then(function(){
				expect(element.all(by.className("help-block")).getText()).toEqual(['Password is required']);
			});		
		});
	});

	it("Essai Register sans FirstName et sans LastName", function(){
		browser.get('http://localhost/register');
		browser.driver.sleep(10000).then(function(){
			var inputUsername=element(by.css('[name="username"]'))
			inputUsername.sendKeys("Username");
			var inputPassword=element(by.css('[name="password"]'))
			inputPassword.sendKeys("Password");
			element.all(by.buttonText("Register")).click();
			browser.driver.sleep(1000).then(function(){
				expect(element.all(by.className("help-block")).getText()).toEqual(['First Name is required', 'Last Name is required']);
			});		
		});
	});

	it("Essai Register sans FirstName et sans Username", function(){
		browser.get('http://localhost/register');
		browser.driver.sleep(10000).then(function(){
			var inputLastname=element(by.css('[name="lastName"]'))
			inputLastname.sendKeys("Lastname");
			var inputPassword=element(by.css('[name="password"]'))
			inputPassword.sendKeys("Password");
			element.all(by.buttonText("Register")).click();
			browser.driver.sleep(1000).then(function(){
				expect(element.all(by.className("help-block")).getText()).toEqual(['First Name is required', 'Username is required']);
			});		
		});
	});

	it("Essai Register sans FirstName et sans Password", function(){
		browser.get('http://localhost/register');
		browser.driver.sleep(10000).then(function(){
			var inputLastname=element(by.css('[name="lastName"]'))
			inputLastname.sendKeys("Lastname");
			var inputPassword=element(by.css('[name="username"]'))
			inputPassword.sendKeys("Username");
			element.all(by.buttonText("Register")).click();
			browser.driver.sleep(1000).then(function(){
				expect(element.all(by.className("help-block")).getText()).toEqual(['First Name is required', 'Password is required']);
			});		
		});
	});

	it("Essai Register sans LastName et sans Username", function(){
		browser.get('http://localhost/register');
		browser.driver.sleep(10000).then(function(){
			var inputLastname=element(by.css('[name="firstName"]'))
			inputLastname.sendKeys("Firstname");
			var inputPassword=element(by.css('[name="password"]'))
			inputPassword.sendKeys("Password");
			element.all(by.buttonText("Register")).click();
			browser.driver.sleep(1000).then(function(){
				expect(element.all(by.className("help-block")).getText()).toEqual(['Last Name is required', 'Username is required']);
			});		
		});
	});

	it("Essai Register sans LastName et sans Password", function(){
		browser.get('http://localhost/register');
		browser.driver.sleep(10000).then(function(){
			var inputLastname=element(by.css('[name="firstName"]'))
			inputLastname.sendKeys("Firstname");
			var inputPassword=element(by.css('[name="username"]'))
			inputPassword.sendKeys("Username");
			element.all(by.buttonText("Register")).click();
			browser.driver.sleep(1000).then(function(){
				expect(element.all(by.className("help-block")).getText()).toEqual(['Last Name is required', 'Password is required']);
			});		
		});
	});

	it("Essai Register sans Username et sans Password", function(){
		browser.get('http://localhost/register');
		browser.driver.sleep(10000).then(function(){
			var inputLastname=element(by.css('[name="firstName"]'))
			inputLastname.sendKeys("Firstname");
			var inputPassword=element(by.css('[name="lastName"]'))
			inputPassword.sendKeys("Lastname");
			element.all(by.buttonText("Register")).click();
			browser.driver.sleep(1000).then(function(){
				expect(element.all(by.className("help-block")).getText()).toEqual(['Username is required', 'Password is required']);
			});		
		});
	});

	it("Essai Register sans LastName, sans Username et sans Password", function(){
		browser.get('http://localhost/register');
		browser.driver.sleep(10000).then(function(){
			var inputLastname=element(by.css('[name="firstName"]'))
			inputLastname.sendKeys("Firstname");
			element.all(by.buttonText("Register")).click();
			browser.driver.sleep(1000).then(function(){
				expect(element.all(by.className("help-block")).getText()).toEqual(['Last Name is required', 'Username is required', 'Password is required']);
			});		
		});
	});

	it("Essai Register sans FirstName, sans Username et sans Password", function(){
		browser.get('http://localhost/register');
		browser.driver.sleep(10000).then(function(){
			var inputLastname=element(by.css('[name="lastName"]'))
			inputLastname.sendKeys("Lastname");
			element.all(by.buttonText("Register")).click();
			browser.driver.sleep(1000).then(function(){
				expect(element.all(by.className("help-block")).getText()).toEqual(['First Name is required', 'Username is required', 'Password is required']);
			});		
		});
	});

	it("Essai Register sans FirstName, sans Lastname et sans Password", function(){
		browser.get('http://localhost/register');
		browser.driver.sleep(10000).then(function(){
			var inputLastname=element(by.css('[name="username"]'))
			inputLastname.sendKeys("Username");
			element.all(by.buttonText("Register")).click();
			browser.driver.sleep(1000).then(function(){
				expect(element.all(by.className("help-block")).getText()).toEqual(['First Name is required', 'Last Name is required', 'Password is required']);
			});		
		});
	});

	it("Essai Register sans FirstName, sans Lastname et sans Username", function(){
		browser.get('http://localhost/register');
		browser.driver.sleep(10000).then(function(){
			var inputLastname=element(by.css('[name="password"]'))
			inputLastname.sendKeys("Password");
			element.all(by.buttonText("Register")).click();
			browser.driver.sleep(1000).then(function(){
				expect(element.all(by.className("help-block")).getText()).toEqual(['First Name is required', 'Last Name is required', 'Username is required']);
			});		
		});
	});

	it("Essai Register sans FirstName, sans Lastname, sans Username et sans Password", function(){
		browser.get('http://localhost/register');
		browser.driver.sleep(10000).then(function(){
			element.all(by.buttonText("Register")).click();
			browser.driver.sleep(1000).then(function(){
				expect(element.all(by.className("help-block")).getText()).toEqual(['First Name is required', 'Last Name is required', 'Username is required', 'Password is required']);
			});		
		});
	});

	it("Verification Cancel", function(){
		browser.get('http://localhost/register');
		browser.driver.sleep(5000).then(function(){
			element.all(by.linkText("Cancel")).click();
			browser.driver.sleep(5000).then(function(){
				expect(browser.getCurrentUrl()).toEqual('http://localhost/login');
			});		
		});
	});

	it("Register User déjà crée", function(){
		browser.get('http://localhost/register');
		browser.driver.sleep(5000).then(function(){
			var inputFirstName=element(by.css('[name="firstName"]'))
			inputFirstName.sendKeys("oui");
			var inputLastname=element(by.css('[name="lastName"]'))
			inputLastname.sendKeys("oui");
			var inputFirstName=element(by.css('[name="username"]'))
			inputFirstName.sendKeys("VincentCabrini");
			var inputPassword=element(by.css('[name="password"]'))
			inputPassword.sendKeys("oui");
			element.all(by.buttonText("Register")).click();
			browser.driver.sleep(1000).then(function(){
				var countErrorMessage=element.all(by.name("Response with status: 401 Unauthorized for URL: http://localhost/restapi/login")).count();
        			expect(countErrorMessage,1);
			});		
		});
	});

	it("Register User non crée", function(){
		browser.get('http://localhost/register');
		browser.driver.sleep(5000).then(function(){
			var inputFirstName=element(by.css('[name="firstName"]'))
			inputFirstName.sendKeys("oui");
			var inputLastname=element(by.css('[name="lastName"]'))
			inputLastname.sendKeys("oui");
			var inputFirstName=element(by.css('[name="username"]'))
			inputFirstName.sendKeys("ouihgdfhdf");
			var inputPassword=element(by.css('[name="password"]'))
			inputPassword.sendKeys("oui");
			element.all(by.buttonText("Register")).click();
			browser.driver.sleep(5000).then(function(){
        			var countMessage=element.all(by.name("Registration successful")).count();
        			expect(countMessage,1);
			});		
		});
	});

	it("Register User non crée et log in", function(){
		browser.get('http://localhost/register');
		browser.driver.sleep(5000).then(function(){
			var inputFirstName=element(by.css('[name="firstName"]'))
			inputFirstName.sendKeys("oui");
			var inputLastname=element(by.css('[name="lastName"]'))
			inputLastname.sendKeys("oui");
			var inputUsername=element(by.css('[name="username"]'))
			inputUsername.sendKeys("ouihgdfdjhf2");
			var inputPassword=element(by.css('[name="password"]'))
			inputPassword.sendKeys("oui");
			element.all(by.buttonText("Register")).click();
			browser.driver.sleep(5000).then(function(){
        			var inputUsernameLog=element(by.css('[name="username"]'))
				inputUsernameLog.sendKeys("ouihgdfdjhf2");
				var inputPasswordLog=element(by.css('[name="password"]'))
				inputPasswordLog.sendKeys("oui");
				browser.driver.sleep(1000).then(function(){
					element.all(by.buttonText("Login")).click();
					browser.driver.sleep(5000).then(function(){
						expect(browser.getCurrentUrl()).toEqual('http://localhost/');
						element(by.linkText("Sign out")).click();
						browser.driver.sleep(3000).then(function(){
						});
					});
				});
			});		
		});
	});


	

});
