# bookstore
# Add a book
Give book details body and count in the url
"/api/add/{count}"
{
	"isbn":"1127",
	"title":"qui est esse",
	"description":"est rerum tempore vitae sequi sint nihil reprehenderit dolor beatae ea dolores neque fugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis qui aperiam non debitis possimus qui neque nisi nulla",
	"author":"Hemant Raj",
	"publisher":"Penguin"
}

#buy a book by book title 
pass title and count in the url
"api/buyABook/{title}/{count}"

#search book based on the ISBN/title/author
"api/search/{params}"

#search media based on the ISBN
pass a ISBN in the url and get matched media in the list
"api/searchMedia/{isbn}"



