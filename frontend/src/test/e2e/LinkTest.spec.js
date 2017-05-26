describe("Link Tests",function(){

	xit("it should confirm if login has link to register", function(){
		browser.get('http://localhost/labCon');
		browser.driver.sleep(5000).then(function(){
		element(by.linkText("Register")).click();
		browser.driver.sleep(5000).then(function(){
			expect(browser.getCurrentUrl()).toEqual('http://localhost/register');
		});
		});		
	});

	xit("it should confirm if register has link to login", function(){
		browser.get('http://localhost/register');
		browser.driver.sleep(5000).then(function(){
		element(by.linkText("Cancel")).click();
		browser.driver.sleep(5000).then(function(){
			expect(browser.getCurrentUrl()).toEqual('http://localhost/login');
		});
		});		
	});

	xit("it should confirm login -> register -> login", function(){
		browser.get('http://localhost/labCon');
		browser.driver.sleep(5000).then(function(){
		element(by.linkText("Register")).click();
		browser.driver.sleep(5000).then(function(){
			element(by.linkText("Cancel")).click();
			browser.driver.sleep(5000).then(function(){
			expect(browser.getCurrentUrl()).toEqual('http://localhost/login');
			});
		});
		});		
	});

	

});
