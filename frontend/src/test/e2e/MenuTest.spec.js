describe("Menu Tests",function(){

	
	it("Test Login -> Home", function(){
		browser.get('http://localhost/login');
		browser.driver.sleep(10000).then(function(){
			var inputUsername=element(by.css('[name="username"]'))
			inputUsername.sendKeys("VincentCabrini");
			var inputPassword=element(by.css('[name="password"]'))
			inputPassword.sendKeys("PasswordVC");
			browser.driver.sleep(1000).then(function(){
				element.all(by.buttonText("Login")).click();
				browser.driver.sleep(10000).then(function(){
					expect(browser.getCurrentUrl()).toEqual('http://localhost/');
				});
			});
		});
	});


	it("Test Light1 Valeur Raffrachie", function(){
		browser.get('http://localhost/light/Light1');
		browser.driver.sleep(2000).then(function(){
			var power1=element(by.cssContainingText("h3","Power consumption")).getText();
			browser.driver.sleep(3500).then(function(){	
				var power2=element(by.cssContainingText("h3","Power consumption")).getText();
				expect(power1).not.toEqual(power2);
			});
		});
		
	});

	it("Test Light2 Valeur Raffrachie", function(){
		browser.get('http://localhost/light/Light2');
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
	
	

	it("Test Live Power Consumption Hub1", function(){
		browser.get('http://localhost/hub/Hub1');
		browser.driver.sleep(2000).then(function(){
			var power1=element(by.cssContainingText("h3","Live power consumption")).getText();
			browser.driver.sleep(3500).then(function(){	
				var power2=element(by.cssContainingText("h3","Live power consumption")).getText();
				expect(power1).not.toEqual(power2);
			});
		});
		
	});

	
	it("Test Socket1 Valeur Raffrachie", function(){
		browser.get('http://localhost/socket/Socket1');
		browser.driver.sleep(2000).then(function(){
			var power1=element(by.cssContainingText("h3","Live power consumption")).getText();
			browser.driver.sleep(3500).then(function(){	
				var power2=element(by.cssContainingText("h3","Live power consumption")).getText();
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
