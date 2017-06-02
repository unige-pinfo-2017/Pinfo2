describe("Login Tests",function(){

	
	it("Test Login -> Home", function(){
		browser.get('http://localhost/');
		browser.driver.sleep(5000).then(function(){
			var inputUsername=element(by.css('[name="username"]'))
			inputUsername.sendKeys("oui");
			var inputPassword=element(by.css('[name="password"]'))
			inputPassword.sendKeys("oui");
			browser.driver.sleep(1000).then(function(){
				element.all(by.buttonText("Login")).click();
				browser.driver.sleep(5000).then(function(){
					expect(browser.getCurrentUrl()).toEqual('http://localhost/');
				});
			});
		});
	});


	it("Test Light 100 Valeur Raffrachie", function(){
		browser.get('http://localhost/light/100');
		browser.driver.sleep(2000).then(function(){
			var power1=element(by.cssContainingText("h3","Power consumption")).getText();
			browser.driver.sleep(3500).then(function(){	
				var power2=element(by.cssContainingText("h3","Power consumption")).getText();
				expect(power1).not.toEqual(power2);
			});
		});
		
	});

	it("Test Light 101 Valeur Raffrachie", function(){
		browser.get('http://localhost/light/101');
		browser.driver.sleep(2000).then(function(){
			var power1=element(by.cssContainingText("h3","Power consumption")).getText();
			browser.driver.sleep(3500).then(function(){	
				var power2=element(by.cssContainingText("h3","Power consumption")).getText();
				expect(power1).not.toEqual(power2);
			});
		});
		
	});
	
	it("Test Light 102 Valeur Raffrachie", function(){
		browser.get('http://localhost/light/102');
		browser.driver.sleep(2000).then(function(){
			var power1=element(by.cssContainingText("h3","Power consumption")).getText();
			browser.driver.sleep(3500).then(function(){	
				var power2=element(by.cssContainingText("h3","Power consumption")).getText();
				expect(power1).not.toEqual(power2);
			});
		});
		
	});

	it("Test Appuyer Bouton State", function(){
		element(by.className('mat-slide-toggle-input cdk-visually-hidden')).click();
		browser.driver.sleep(3000).then(function(){
			element(by.className('mat-slide-toggle-input cdk-visually-hidden')).click();
			browser.driver.sleep(3000).then(function(){
			});
		});
		
	});

	it("Test Appuyer Bouton Socket 201 Hub 110", function(){
		browser.get('http://localhost/hub/110');
		browser.driver.sleep(2000).then(function(){
			element(by.id('md-slide-toggle-2-input')).click();
			browser.driver.sleep(3000).then(function(){
				element(by.id('md-slide-toggle-2-input')).click();
				browser.driver.sleep(3000).then(function(){
				});
			});
		});
		
	});

	it("Test Appuyer Lien Socket 200 Hub 110", function(){
		browser.driver.sleep(2000).then(function(){
			element(by.cssContainingText("p","Socket 200")).click();
			browser.driver.sleep(3000).then(function(){
				expect(browser.getCurrentUrl()).toEqual('http://localhost/socket/200');
			});
		});
		
	});

	it("Test Socket 200 Valeur Raffrachie", function(){
		browser.driver.sleep(2000).then(function(){
			var power1=element(by.cssContainingText("h3","Power consumption")).getText();
			browser.driver.sleep(3500).then(function(){	
				var power2=element(by.cssContainingText("h3","Power consumption")).getText();
				expect(power1).not.toEqual(power2);
			});
		});
		
	});

	it("Test Appuyer Bouton State Socket", function(){
		element(by.className('mat-slide-toggle-input cdk-visually-hidden')).click();
		browser.driver.sleep(3000).then(function(){
			element(by.className('mat-slide-toggle-input cdk-visually-hidden')).click();
			browser.driver.sleep(3000).then(function(){
			});
		});
		
	});

	

	it("Logout Test", function(){
		browser.driver.sleep(1000).then(function(){
			element.all(by.buttonText("Login")).click();
			browser.driver.sleep(5000).then(function(){
				element(by.linkText("Sign out")).click();
				browser.driver.sleep(3000).then(function(){
					expect(browser.getCurrentUrl()).toEqual('http://localhost/login');
				});
			});
		});
	});









});
