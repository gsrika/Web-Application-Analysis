When(/^A new user wants to create a an account$/) do
  visit('/bookstore/Registration.jsp')
end

When(/^fill in "(.*?)" as "(.*?)"$/) do |arg1, arg2|
  fill_in arg1,:with => arg2
end

When(/^click the button "(.*?)"$/) do |arg1|
    click_button(arg1)
end

Then(/^load  page "(.*?)"$/) do |arg1|
  visit(arg1)
end

When(/^verify content "(.*?)"$/) do |arg1|
  page.has_content?(arg1)
end

When(/^click the link  "(.*?)"$/) do |arg1|
   click_link(arg1)	
end

When(/^verify content not present "(.*?)"$/) do |arg1|
 page.should have_no_content(arg1)
end

When(/^click the link by xpath "(.*?)"$/) do |arg1|
 find(:xpath, arg1).click
end

When(/^click the delte "(.*?)"$/) do |arg1|
  click_button(arg1)
 page.driver.browser.switch_to.alert.accept
end

When(/^delete the MySQL & PHP book from shoppingcart$/) do
find(:xpath, "//tr[3]/td/a/font").click
end

When(/^click on the logout page$/) do
  find(:xpath, "//td[6]/a/font/img").click
end


