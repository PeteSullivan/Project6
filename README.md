# Project6

Simple notes app using Android Studio/Kotlin. 

## Functionality 

The following **required** functionality is completed:

* [ ] User can view all notes in the database
* [ ] User can edit a note, which is updated in the database
* [ ] UI is dynamic and user can interact with it.
* [ ] Runs without crashing

The following **extensions** are implemented:

N/A

## Video Walkthrough

Here's a walkthrough of implemented user stories:

![project6gif](https://github.com/PeteSullivan/Project6/assets/107013174/fb4f7445-0058-401a-9b9e-f6a3537a19d3)

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

I had a bug where I couldn't pass arguments between fragments. This took up a lot of time during this project and 
ultimately I didn't find a way around it. I tried to use bundles, a shared view model, and arguments in other ways,
but nothing worked. However, I hard coded the app to edit the 2nd note no matter what is clicked. This way, you can
see the database/note editing/saving is all working and the argument bug is the major problem. I also didn't get to 
deleting the notes or adding the popup confirm text because I ran out of time.

## License

    Copyright [2023] [Pete Sullivan]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
