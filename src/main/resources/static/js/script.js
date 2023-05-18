
function toggleElementVisibility(elementId) {
 const element = document.getElementById(elementId);
 if (element.style.display === 'none') {
  element.style.display = 'block';
  localStorage.setItem(elementId, 'visible');
 } else {
  element.style.display = 'none';
  localStorage.setItem(elementId, 'hidden');
 }
}
//récupère l'état du toggle
window.addEventListener('load', function() {
 const elementIds = ['my-element', 'another-element']; //  éléments  restaurer
 elementIds.forEach(function(elementId) {
  const state = localStorage.getItem(elementId);
  if (state === 'visible') {
   const element = document.getElementById(elementId);
   element.style.display = 'block';
  } else if (state === 'hidden') {
   const element = document.getElementById(elementId);
   element.style.display = 'none';
  }
 });
});


