describe("Login Tests",function(){

	xit("it should confirm if Register word exists", function(){
		browser.get('http://localhost/register');
		browser.driver.sleep(5000).then(function(){
		var countNameLogin=element.all(by.name("Register")).count();
		expect(countNameLogin,1);
		});		
	});

	xit("it should confirm if Firstname word exists", function(){
		var countNameUsername=element.all(by.name("First Name")).count();
		expect(countNameUsername,1);
	});

	xit("it should confirm if Lastname word exists", function(){
		var countNamePassword=element.all(by.name("Last Name")).count();
		expect(countNamePassword,1);
	});

	xit("it should confirm if Username button exists", function(){
		var countNameUsername=element.all(by.name("Username")).count();
		expect(countNameUsername,1);
	});

	xit("it should confirm if Password button exists", function(){
		var countNamePassword=element.all(by.name("Password")).count();
		expect(countNamePassword,1);
	});

	xit("it should confirm if there are 4 inputs", function(){
		var countButtonRegister=element.all(by.tagName("input")).count();
		expect(countButtonRegister,4);
	});

	xit("it should confirm if Register button exists", function(){
		var countButtonRegister=element.all(by.buttonText("Register")).count();
		expect(countButtonRegister,1);
	});

	xit("it should confirm if Cancel button exists", function(){
		var countButtonRegister=element.all(by.linkText("Cancel")).count();
		expect(countButtonRegister,1);
	});


	

});
