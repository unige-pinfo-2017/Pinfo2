describe("Login Tests",function(){

	it("it should confirm if Login word exists", function(){
		browser.get('http://localhost/labCon/');
		browser.driver.sleep(5000).then(function(){
		var countNameLogin=element.all(by.name("Login")).count();
		expect(countNameLogin,1);
		});		
	});

	it("it should confirm if Username word exists", function(){
		var countNameUsername=element.all(by.name("Username")).count();
		expect(countNameUsername,1);
	});

	it("it should confirm if Password word exists", function(){
		var countNamePassword=element.all(by.name("Password")).count();
		expect(countNamePassword,1);
	});

	it("it should confirm if Login button exists", function(){
		var countButtonLogin=element.all(by.buttonText("Login")).count();
		expect(countButtonLogin,1);
	});

	it("it should confirm if Register button exists", function(){
		var countButtonRegister=element.all(by.buttonText("Register")).count();
		expect(countButtonRegister,1);
		element.all(by.buttonText("Register")).click();
	});

	it("it should confirm if there are 2 inputs", function(){
		var countButtonRegister=element.all(by.tagName("input")).count();
		expect(countButtonRegister,2);
	});
	

});
