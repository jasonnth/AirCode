__d(function(e,t,i,n){"use strict";function o(e){if(e&&e.__esModule)return e;var t={};if(null!=e)for(var i in e)Object.prototype.hasOwnProperty.call(e,i)&&(t[i]=e[i]);return t.default=e,t}Object.defineProperty(n,"__esModule",{value:!0}),n.GuidebookClickPlacePdpInfoSectionEvent=void 0;var a=t(412),u=function(e){return e&&e.__esModule?e:{default:e}}(a),d=t(694),r=o(d),l=t(2095),p=o(l),c=t(698),s=o(c);n.GuidebookClickPlacePdpInfoSectionEvent={defaultProps:{schema:"com.airbnb.jitney.event.logging.Guidebook:GuidebookClickPlacePdpInfoSectionEvent:3.0.0",event_name:"guidebook_click_place_pdp_info_section",page:"place_pdp",operation:2},propTypes:{schema:u.default.string,event_name:u.default.string.isRequired,context:u.default.shape(r.Context.propTypes).isRequired,page:u.default.string.isRequired,operation:u.default.oneOf(Object.values(s.Operation)).isRequired,target:u.default.string.isRequired,place_id:u.default.number.isRequired,place_pdp_type:u.default.oneOf(Object.values(p.PlacePdpType)).isRequired}}},2099);