__d(function(e,t,r,i){function _(e){e&&o.default.push(s.SHARING,{entry_point:"experience",id:e.id,url:e.share_url||(0,n.getWebUrl)("/city-hosts/"+e.id),primary_image_url:e.poster_pictures&&e.poster_pictures[0]&&e.poster_pictures[0].poster,product_type:e.product_type,location:e.market.name,experience_title:e.title,experience_description:e.what_to_expect,host_name:e.experience_host_profile.host.first_name,fb_share_url:e.fb_share_url})}function p(e){if(e){var t=e.product_type===c.IMMERSION?E.default.SOURCE.TRIP_TEMPLATE_SCREEN:E.default.SOURCE.SINGLE_EXPERIENCE_TRIP_TEMPLATE_SCREEN;E.default.toggleTripTemplateWishListed(e.id,t)}}Object.defineProperty(i,"__esModule",{value:!0}),i.PRODUCT_TYPE=void 0,i.showShareTemplateSheet=_,i.toggleWishListTemplate=p;var l=t(410),o=babelHelpers.interopRequireDefault(l),a=t(1482),s=babelHelpers.interopRequireWildcard(a),u=t(714),E=babelHelpers.interopRequireDefault(u),n=t(1483),c=i.PRODUCT_TYPE={IMMERSION:0,SINGLE_EXPERIENCE:1}},1675);