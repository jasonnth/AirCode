__d(function(r,n,f,e){"use strict";var a=n(47),i=n(48),l=function(r,n,f,e,l,b){var t=n[f];if(void 0!==t&&null!==t){if("number"!=typeof t&&null===i(t)){var u=a[l];return new Error("Invalid "+u+" `"+(b||f)+"` supplied to `"+e+"`: "+t+"\nValid color formats are\n  - '#f0f' (#rgb)\n  - '#f0fc' (#rgba)\n  - '#ff00ff' (#rrggbb)\n  - '#ff00ff00' (#rrggbbaa)\n  - 'rgb(255, 255, 255)'\n  - 'rgba(255, 255, 255, 1.0)'\n  - 'hsl(360, 100%, 100%)'\n  - 'hsla(360, 100%, 100%, 1.0)'\n  - 'transparent'\n  - 'red'\n  - 0xff00ff00 (0xrrggbbaa)\n")}}else if(r){var u=a[l];return new Error("Required "+u+" `"+(b||f)+"` was not specified in `"+e+"`.")}},b=l.bind(null,!1);b.isRequired=l.bind(null,!0),f.exports=b},46);