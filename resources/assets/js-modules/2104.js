__d(function(e,i,t,r){"use strict";function d(e){if(e&&e.__esModule)return e;var i={};if(null!=e)for(var t in e)Object.prototype.hasOwnProperty.call(e,t)&&(i[t]=e[t]);return i.default=e,i}Object.defineProperty(r,"__esModule",{value:!0}),r.GuidebookClickInsiderExperienceAddToWishlistEvent=void 0;var n=i(412),s=function(e){return e&&e.__esModule?e:{default:e}}(n),u=i(694),o=d(u),a=i(2098),l=d(a),c=i(698),_=d(c);r.GuidebookClickInsiderExperienceAddToWishlistEvent={defaultProps:{schema:"com.airbnb.jitney.event.logging.Guidebook:GuidebookClickInsiderExperienceAddToWishlistEvent:1.0.0",event_name:"guidebook_click_insider_experience_add_to_wishlist",page:"insider_guidebook",operation:2,target:"wishlist_button"},propTypes:{schema:s.default.string,event_name:s.default.string.isRequired,context:s.default.shape(o.Context.propTypes).isRequired,page:s.default.string.isRequired,operation:s.default.oneOf(Object.values(_.Operation)).isRequired,target:s.default.string.isRequired,insider_id:s.default.number.isRequired,insider_type:s.default.oneOf(Object.values(l.InsiderType)).isRequired,experience_id:s.default.number.isRequired,search_id:s.default.string.isRequired,mobile_search_session_id:s.default.string.isRequired,uuid:s.default.string.isRequired}}},2104);