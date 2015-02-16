# 

Android App for doing Google Image Searches which allows a user to select search filters and paginate results infinitely.
Time spent: Around 20-22 hours in total

Completed user stories:

 * [x] Required: User can enter a search query that will display a grid of image results from the Google Image API.
 * [x] Required: User can click on "settings" which allows selection of advanced search options to filter results
 * [x] Required: User can configure advanced search filters such as:
 * [x] Required: Size (small, medium, large, extra-large)
        ** Color filter (black, blue, brown, gray, green, etc...)
        ** Type (faces, photo, clip art, line art)
        ** Site (espn.com)
 * [x] Required: Subsequent searches will have any filters applied to the search results
 * [x] Required: User can tap on any image in results to see the image full-screen
 * [x] Required: User can scroll down “infinitely” to continue loading more image results (up to 8 pages)

 * [x] Optional Advanced: Robust error handling, check if internet is available, handle error cases, network failures
 * [x] Optional Advanced: Use the ActionBar SearchView or custom layout as the query box instead of an EditText
 * [x] Optional Advanced: Replace Filter Settings Activity with a lightweight modal overlay - Used dialog Fragment here
 * [x] Optional Advanced: Improve the user interface and experiment with image assets and/or styling and coloring
 * [x] Optional Bonus: Use the StaggeredGridView to display improve the grid of image results


Walkthrough of all user stories:

![Video Walkthrough](demo-googleimagesearch-app.gif)

GIF created with [LiceCap](http://www.cockos.com/licecap/).
