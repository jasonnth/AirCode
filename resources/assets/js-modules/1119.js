__d(function(n,r,t,a){Object.defineProperty(a,"__esModule",{value:!0});var u=r(932);a.default={circle:function(n,r,t){return"M "+n+", "+r+" m "+-t+", 0\n      a "+t+", "+t+" 0 1,0 "+2*t+",0\n      a "+t+", "+t+" 0 1,0 "+2*-t+",0"},square:function(n,r,t){var a=.87*t;return"M "+(n-a)+", "+(r+a)+"\n      L "+(n+a)+", "+(r+a)+"\n      L "+(n+a)+", "+(r-a)+"\n      L "+(n-a)+", "+(r-a)+"\n      z"},diamond:function(n,r,t){var a=.87*t,u=Math.sqrt(a*a*2);return"M "+n+", "+(r+u)+"\n      L "+(n+u)+", "+r+"\n      L "+n+", "+(r-u)+"\n      L "+(n-u)+", "+r+"\n      z"},triangleDown:function(n,r,t){return"M "+(n-t)+", "+(r-t)+"\n      L "+(n+t)+", "+(r-t)+"\n      L "+n+", "+(r+t/2*Math.sqrt(3))+"\n      z"},triangleUp:function(n,r,t){return"M "+(n-t)+", "+(r+t)+"\n      L "+(n+t)+", "+(r+t)+"\n      L "+n+", "+(r-t/2*Math.sqrt(3))+"\n      z"},plus:function(n,r,t){var a=1.1*t;return"M "+(n-a/2.5)+", "+(r+a)+"\n      L "+(n+a/2.5)+", "+(r+a)+"\n      L "+(n+a/2.5)+", "+(r+a/2.5)+"\n      L "+(n+a)+", "+(r+a/2.5)+"\n      L "+(n+a)+", "+(r-a/2.5)+"\n      L "+(n+a/2.5)+", "+(r-a/2.5)+"\n      L "+(n+a/2.5)+", "+(r-a)+"\n      L "+(n-a/2.5)+", "+(r-a)+"\n      L "+(n-a/2.5)+", "+(r-a/2.5)+"\n      L "+(n-a)+", "+(r-a/2.5)+"\n      L "+(n-a)+", "+(r+a/2.5)+"\n      L "+(n-a/2.5)+", "+(r+a/2.5)+"\n      z"},star:function(n,r,t){var a=1.35*t,L=Math.PI/5;return"M "+(0,u.range)(10).map(function(t){var u=t%2==0?a:a/2;return u*Math.sin(L*(t+1))+n+",\n        "+(u*Math.cos(L*(t+1))+r)}).join("L")+" z"}}},1119);