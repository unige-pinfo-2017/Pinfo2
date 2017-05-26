describe("Login Tests",function(){

	it("it should confirm if Login word exists", function(){
		browser.get('http://localhost/labCon/');
		browser.driver.sleep(5000).then(function(){
		var countNameLogin=element.all(by.name("Login")).count();
		expect(countNameLogin,1);
		});		
	});

	xit("it should confirm if Username word exists", function(){
		var countNameUsername=element.all(by.name("Username")).count();
		expect(countNameUsername,1);
	});

	xit("it should confirm if Password word exists", function(){
		var countNamePassword=element.all(by.name("Password")).count();
		expect(countNamePassword,1);
	});

	xit("it should confirm if Login button exists", function(){
		var countButtonLogin=element.all(by.buttonText("Login")).count();
		expect(countButtonLogin,1);
	});

	xit("it should confirm if Register button exists", function(){
		var countButtonRegister=element.all(by.linkText("Register")).count();
		expect(countButtonRegister,1);
	});

	xit("it should confirm if there are 2 inputs", function(){
		var countButtonRegister=element.all(by.tagName("input")).count();
		expect(countButtonRegister,2);
	});
	
	it("it should add value to input username", function(){
		element(by.css('[name="username"]')).sendKeys("aaaaaaaaa");
		browser.driver.sleep(1000).then(function(){
			expect(element(by.css('[name="username"]')).getKeys()).toEqual("aaaaaaaaa");
		});
	});

	xit("it should add value to input password", function(){
		element(by.css('[name="password"]')).sendKeys("Password");
		browser.driver.sleep(1000).then(function(){
			expect(element(by.css('[name="password"]'))).toEqual("Password");
		});
	});

});
