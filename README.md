# polish-notation
Infix notation to polish notation converter
## Run with docker
Clone repository 
```console
git clone https://github.com/zadorotskas/polish-notation.git
cd polish-notation
```
Build docker image
```console
sudo docker build -t polish-notation .
```
Run docker image
```console
sudo docker run -p 8080:8080 polish-notation
```
## Usage
Send POST-request in Postman: 
1. `0.0.0.0:8080/api/toPolish` with parameters
   1. `infix` - math expression in infix notation
   2. `calculate` - if set true then after expression appears `=` with result 
2. `0.0.0.0:8080/api/fromPolish` with parameters
   1. `polish` - math expression in polish notation
   2. `calculate` - same as `toPolish`

## Examples
1. On request `http://0.0.0.0:8080/api/toPolish?infix=8*(8-6)` \
Response is `8 8 6 - *`
2. Add `calculate` parameter `http://0.0.0.0:8080/api/toPolish?infix=8*(8-6)&calculate=true` \
Response - `8 8 6 - * = 16`
3. Request `http://0.0.0.0:8080/api/fromPolish?polish=8+8+/+3+*+4+-` \
Response - `(((8/8)*3)-4)`
4. Request `http://0.0.0.0:8080/api/fromPolish?polish=8+8+/+3+*+4+-&calculate=true` \
Response - `(((8/8)*3)-4) = -1`