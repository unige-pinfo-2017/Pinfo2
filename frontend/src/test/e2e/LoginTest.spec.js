describe("Login Tests",function(){

	xit("Url Login", function(){
		browser.get('http://localhost/');
		browser.driver.sleep(5000).then(function(){
			expect(browser.getCurrentUrl()).toEqual('http://localhost/login?returnUrl=%2F');
		});		
	});

	xit("Compte le nombre de champs", function(){
		expect(element.all(by.id("input")).count(),2);	
	});

	xit("Compte le nombre de boutons", function(){
		expect(element.all(by.id("button")).count(),2);	
	});

	xit("Verifie si il y a un bouton Login", function(){
		expect(element.all(by.buttonText("Login")).count(),1);	
	});

	xit("Verifie si il y a un bouton Register", function(){
		expect(element.all(by.linkText("Register")).count(),1);
	});

	xit("Ajoute un String au champ Username", function(){
		var inputUsername=element(by.css('[name="username"]'))
		inputUsername.sendKeys("Username");
		browser.driver.sleep(1000).then(function(){
			inputUsername.getAttribute("ng-reflect-model").then(function(value){
			expect(value).toEqual("Username");
			});
		});
	});

	xit("Ajoute un String au champ Password", function(){
		var inputPassword=element(by.css('[name="password"]'))
		inputPassword.sendKeys("Password");
		browser.driver.sleep(1000).then(function(){
			inputPassword.getAttribute("ng-reflect-model").then(function(value){
				expect(value).toEqual("Password");
			});
		});
	});

	xit("Essai Login sans password", function(){
		browser.get('http://localhost/');
		browser.driver.sleep(5000).then(function(){
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

	xit("Essai Login sans Username", function(){
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

	xit("Essai Login sans Username et sans Password", function(){
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
	//PEUT ETRE UN BUG
	xit("Essai acces menu hub sans login", function(){
		browser.get('http://localhost/hub');
		browser.driver.sleep(5000).then(function(){
			expect(browser.getCurrentUrl()).toEqual('http://localhost/login?returnUrl=%2Fhub');
		});
	});

	xit("Essai acces menu light sans login", function(){
		browser.get('http://localhost/light');
		browser.driver.sleep(5000).then(function(){
			expect(browser.getCurrentUrl()).toEqual('http://localhost/login?returnUrl=%2F');
		});
	});
	
	xit("Essai acces menu socket sans login", function(){
		browser.get('http://localhost/socket');
		browser.driver.sleep(5000).then(function(){
			expect(browser.getCurrentUrl()).toEqual('http://localhost/login?returnUrl=%2F');
		});
	});

	xit("Essai acces hub particulier sans login", function(){
		browser.get('http://localhost/hub/110');
		browser.driver.sleep(5000).then(function(){
			expect(browser.getCurrentUrl()).toEqual('http://localhost/login?returnUrl=%2Fhub%2F110');
		});
	});

	xit("Essai acces light particulier sans login", function(){
		browser.get('http://localhost/light/100');
		browser.driver.sleep(5000).then(function(){
			expect(browser.getCurrentUrl()).toEqual('http://localhost/login?returnUrl=%2Flight%2F100');
		});
	});

	xit("Essai acces socket particulier sans login", function(){
		browser.get('http://localhost/socket/200');
		browser.driver.sleep(5000).then(function(){
			expect(browser.getCurrentUrl()).toEqual('http://localhost/login?returnUrl=%2Fsocket%2F200');
		});
	});


	xit("Bon Username mauvais password", function(){
		browser.get('http://localhost/');
		browser.driver.sleep(5000).then(function(){
			var inputUsername=element(by.css('[name="username"]'))
			inputUsername.sendKeys("oui");
			var inputPassword=element(by.css('[name="password"]'))
			inputPassword.sendKeys("jkhf");
			browser.driver.sleep(1000).then(function(){
				var countErrorMessage=element.all(by.name("Response with status: 401 Unauthorized for URL: http://localhost/restapi/login")).count();
        			expect(countErrorMessage,1);
			});
		});
	});

	xit("Bon Password mauvais username", function(){
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

	xit("Mauvais Password mauvais username", function(){
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

	xit("Login->Register", function(){
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

	xit("Login->Register->Login", function(){
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

	xit("Login", function(){
		browser.get('http://localhost/');
		browser.driver.sleep(5000).then(function(){
			var inputUsername=element(by.css('[name="username"]'))
			inputUsername.sendKeys("oui");
			var inputPassword=element(by.css('[name="password"]'))
			inputPassword.sendKeys("oui");
			browser.driver.sleep(1000).then(function(){
				element.all(by.buttonText("Login")).click();
				browser.driver.sleep(5000).then(function(){
					expect(browser.getCurrentUrl()).toEqual('http://localhost/login?returnUrl=%2F');
					expect(element.all(by.name("You're logged in oui !")).count(),1);
				});
			});
		});
	});

	xit("Mot Username existe", function(){
		browser.get('http://localhost/');
		browser.driver.sleep(5000).then(function(){
			var countNameUsername=element.all(by.name("Username")).count();
			expect(countNameUsername,1);
		});
	});

	xit("Mot Password existe", function(){
		browser.get('http://localhost/');
		browser.driver.sleep(5000).then(function(){
			var countNamePassword=element.all(by.name("Password")).count();
			expect(countNamePassword,1);
		});
	});

	xit("Mot Login existe", function(){
		browser.get('http://localhost/');
		browser.driver.sleep(5000).then(function(){
			var countNameLogin=element.all(by.name("Login")).count();
			expect(countNameLogin,1);
		});		
	});
});
