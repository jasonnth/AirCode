__d(function(e,i,t,r){"use strict";function a(e){if(e&&e.__esModule)return e;var i={};if(null!=e)for(var t in e)Object.prototype.hasOwnProperty.call(e,t)&&(i[t]=e[t]);return i.default=e,i}Object.defineProperty(r,"__esModule",{value:!0}),r.GuidebookClickPlacePdpAddItineraryConfirmEvent=void 0;var d=i(412),n=function(e){return e&&e.__esModule?e:{default:e}}(d),o=i(694),u=a(o),l=i(2095),p=a(l),c=i(698),f=a(c);r.GuidebookClickPlacePdpAddItineraryConfirmEvent={defaultProps:{schema:"com.airbnb.jitney.event.logging.Guidebook:GuidebookClickPlacePdpAddItineraryConfirmEvent:1.0.0",event_name:"guidebook_click_place_pdp_add_itinerary_confirm",page:"place_pdp",operation:2,target:"add_itinerary_confirm"},propTypes:{schema:n.default.string,event_name:n.default.string.isRequired,context:n.default.shape(u.Context.propTypes).isRequired,page:n.default.string.isRequired,operation:n.default.oneOf(Object.values(f.Operation)).isRequired,target:n.default.string.isRequired,place_id:n.default.number.isRequired,place_pdp_type:n.default.oneOf(Object.values(p.PlacePdpType)).isRequired}}},2101);