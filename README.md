# Maybach
Fast and tiny (~16k aar) library that simplifies the communication between different application components.

## Getting started

Create an event:

```java
Event<AnyObject> yourEvent = new Event<AnyObject>("eventName") {
    @Override
    protected void onEvent(AnyObject object) {
        // do stuff with object
    }
};
```

Sign up for the event and unregister from it based on the lifecycle:
   
```java
// activity code

@Override
public void onStart() {
    super.onStart();
    Maybach.get().signUpForEvent(this, yourEvent);
}

@Override
public void onStop() {
    super.onStop();
    Maybach.get().unregisterClassEvents(this);
}
```

Trigger the event from somewhere else in your application:

```java
Maybach.get().triggerEvent("eventName", new AnyObject());
```

## Add Maybach to your project

Gradle:
```gradle
compile 'com.alheap.maybach:maybach:1.0.1'
```

Maven:
```xml
<dependency>
  <groupId>com.alheap.maybach</groupId>
  <artifactId>maybach</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```

## License
    MIT License

    Copyright (c) 2016 Alex Kamenkov

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
