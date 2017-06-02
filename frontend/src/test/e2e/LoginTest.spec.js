describe("Login Tests",function(){

	it("Url Login", function(){
		browser.get('http://localhost/login');
		browser.driver.sleep(10000).then(function(){
			expect(browser.getCurrentUrl()).toEqual('http://localhost/login');
		});		
	});

	it("Compte le nombre de champs", function(){
		expect(element.all(by.id("input")).count(),2);	
	});

	it("Compte le nombre de boutons", function(){
		expect(element.all(by.id("button")).count(),2);	
	});

	it("Verifie si il y a un bouton Login", function(){
		expect(element.all(by.buttonText("Login")).count(),1);	
	});

	it("Verifie si il y a un bouton Register", function(){
		expect(element.all(by.linkText("Register")).count(),1);
	});

	it("Ajoute un String au champ Username", function(){
		var inputUsername=element(by.css('[name="username"]'))
		inputUsername.sendKeys("Username");
		browser.driver.sleep(2000).then(function(){
			inputUsername.getAttribute("ng-reflect-model").then(function(value){
			expect(value).toEqual("Username");
			});
		});
	});

	it("Ajoute un String au champ Password", function(){
		var inputPassword=element(by.css('[name="password"]'))
		inputPassword.sendKeys("Password");
		browser.driver.sleep(2000).then(function(){
			inputPassword.getAttribute("ng-reflect-model").then(function(value){
				expect(value).toEqual("Password");
			});
		});
	});

	it("Essai Login sans password", function(){
		browser.get('http://localhost/login');
		browser.driver.sleep(10000).then(function(){
			var inputUsername=element(by.css('[name="username"]'))
			inputUsername.sendKeys("Username");
			browser.driver.sleep(1000).then(function(){
				element.all(by.buttonText("Login")).click();
				browser.driver.sleep(1000).then(function(){
					var countErrorMessage=element.all(by.className("help-block")).getText();
		
					expect(countErrorMessage).toEqual(['Password is required']);
				});
			});
		});
	});

	it("Essai Login sans Username", function(){
		browser.get('http://localhost/');
		browser.driver.sleep(5000).then(function(){
			var inputPassword=element(by.css('[name="password"]'))
			inputPassword.sendKeys("Password");
			browser.driver.sleep(1000).then(function(){
				element.all(by.buttonText("Login")).click();
				browser.driver.sleep(1000).then(function(){
					var countErrorMessage=element.all(by.className("help-block")).getText();
		
					expect(countErrorMessage).toEqual(['Username is required']);
				});
			});
		});
	});

	it("Essai Login sans Username et sans Password", function(){
		browser.get('http://localhost/');
		browser.driver.sleep(5000).then(function(){
			element.all(by.buttonText("Login")).click();
			browser.driver.sleep(1000).then(function(){
				var countErrorMessage=element.all(by.className("help-block")).count();
				expect(countErrorMessage,2);
				expect(element.all(by.className("help-block")).getText()).toEqual(['Username is required', 'Password is required']);
			});		
		});
	});

	it("Essai acces menu hub sans login", function(){
		browser.get('http://localhost/hub');
		browser.driver.sleep(10000).then(function(){
			expect(browser.getCurrentUrl()).toEqual('http://localhost/login?returnUrl=%2Fhub');
		});
	});

	it("Essai acces menu light sans login", function(){
		browser.get('http://localhost/light');
		browser.driver.sleep(10000).then(function(){
			expect(browser.getCurrentUrl()).toEqual('http://localhost/login?returnUrl=%2F');
		});
	});

	it("Essai acces menu socket sans login", function(){
		browser.get('http://localhost/socket');
		browser.driver.sleep(5000).then(function(){
			expect(browser.getCurrentUrl()).toEqual('http://localhost/login?returnUrl=%2F');
		});
	});

	it("Essai acces hub particulier sans login", function(){
		browser.get('http://localhost/hub/110');
		browser.driver.sleep(5000).then(function(){
			expect(browser.getCurrentUrl()).toEqual('http://localhost/login?returnUrl=%2Fhub%2F110');
		});
	});

	it("Essai acces light particulier sans login", function(){
		browser.get('http://localhost/light/100');
		browser.driver.sleep(5000).then(function(){
			expect(browser.getCurrentUrl()).toEqual('http://localhost/login?returnUrl=%2Flight%2F100');
		});
	});

	it("Essai acces socket particulier sans login", function(){
		browser.get('http://localhost/socket/200');
		browser.driver.sleep(5000).then(function(){
			expect(browser.getCurrentUrl()).toEqual('http://localhost/login?returnUrl=%2Fsocket%2F200');
		});
	});

	it("Bon Username mauvais password", function(){
		browser.get('http://localhost/');
		browser.driver.sleep(5000).then(function(){
			var inputUsername=element(by.css('[name="username"]'))
			inputUsername.sendKeys("max");
			var inputPassword=element(by.css('[name="password"]'))
			inputPassword.sendKeys("max2");
			browser.driver.sleep(3000).then(function(){
				var countErrorMessage=element.all(by.name("Response with status: 401 Unauthorized for URL: http://localhost/restapi/login")).count();
				
				expect(countErrorMessage,1);
				
        			
				
			});
		});
	});

	it("Bon Password mauvais username", function(){
		browser.get('http://localhost/');
		browser.driver.sleep(5000).then(function(){
			var inputUsername=element(by.css('[name="username"]'))
			inputUsername.sendKeys("jkhf");
			var inputPassword=element(by.css('[name="password"]'))
			inputPassword.sendKeys("oui");
			browser.driver.sleep(1000).then(function(){
				var countErrorMessage=element.all(by.name("Response with status: 401 Unauthorized for URL: http://localhost/restapi/login")).count();
        			expect(countErrorMessage,1);
			});
		});
	});

	it("Mauvais Password mauvais username", function(){
		browser.get('http://localhost/');
		browser.driver.sleep(5000).then(function(){
			var inputUsername=element(by.css('[name="username"]'))
			inputUsername.sendKeys("jkhf");
			var inputPassword=element(by.css('[name="password"]'))
			inputPassword.sendKeys("jkhf");
			browser.driver.sleep(1000).then(function(){
				var countErrorMessage=element.all(by.name("Response with status: 401 Unauthorized for URL: http://localhost/restapi/login")).count();
        			expect(countErrorMessage,1);
			});
		});
	});

	it("Login->Register", function(){
		browser.get('http://localhost/');
		browser.driver.sleep(5000).then(function(){
			element.all(by.linkText("Register")).click();
			browser.driver.sleep(1000).then(function(){
				browser.driver.sleep(5000).then(function(){
					expect(browser.getCurrentUrl()).toEqual('http://localhost/register');
				});
			});
		});
	});

	it("Login->Register->Login", function(){
		browser.get('http://localhost/');
		browser.driver.sleep(5000).then(function(){
			element.all(by.linkText("Register")).click();
			browser.driver.sleep(1000).then(function(){
				element.all(by.linkText("Cancel")).click();
				browser.driver.sleep(1000).then(function(){
				expect(browser.getCurrentUrl()).toEqual('http://localhost/login');

				});
			});
		});
	});

	it("Login", function(){
		browser.get('http://localhost/');
		browser.driver.sleep(5000).then(function(){
			var inputUsername=element(by.css('[name="username"]'))
			inputUsername.sendKeys("VincentCabrini");
			var inputPassword=element(by.css('[name="password"]'))
			inputPassword.sendKeys("PasswordVC");
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

	it("Mot Username existe", function(){
		browser.get('http://localhost/');
		browser.driver.sleep(5000).then(function(){
			var countNameUsername=element.all(by.name("Username")).count();
			expect(countNameUsername,1);
		});
	});

	it("Mot Password existe", function(){
		browser.get('http://localhost/');
		browser.driver.sleep(5000).then(function(){
			var countNamePassword=element.all(by.name("Password")).count();
			expect(countNamePassword,1);
		});
	});

	it("Mot Login existe", function(){
		browser.get('http://localhost/');
		browser.driver.sleep(5000).then(function(){
			var countNameLogin=element.all(by.name("Login")).count();
			expect(countNameLogin,1);
		});		
	});
});
