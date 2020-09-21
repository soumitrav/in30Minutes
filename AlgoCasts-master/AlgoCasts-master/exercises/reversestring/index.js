// --- Directions
// Given a string, return a new string with the reversed
// order of characters
// --- Examples
//   reverse('apple') === 'leppa'
//   reverse('hello') === 'olleh'
//   reverse('Greetings!') === '!sgniteerG'

function reverse(str) {
   var arr = str.split(''); 
   debugger;
   var reverse='';
   for(let character of str){
         reverse = character+reverse
         debugger;
   }
   return reverse;
}
reverse('Soumitra');
module.exports = reverse;

// function reverse(str) {
//     return str.split('').reverse().join('');
// }

// function reverse(str) {
//     let reverse = '';
//     for(let char of str){
//         reverse = char+reverse;
//     }
//     return reverse;
//  }