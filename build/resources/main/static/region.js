class Location {
    static #default = '---'; // 기본 값(미정), 예외 발생시 이 값을 리턴 시킵니다.

    _constructor() {
    }

    static initRegionSelectBox() {
        var result = navigator.geolocation.getCurrentPosition(this.reverseGeocoding, (error) => this.regionFetchError(error.message));

        region = result;
    }

    static reverseGeocoding (position) {
        const url = `https://geocode.maps.co/reverse?lat=${position.coords.latitude}&lon=${position.coords.longitude}`;

        fetch(url)
            .then((res) => res.json())
            .then((json) => {
                // 임시 코드, 변수명이 안 정해져서 임시로 값을 만들어 넣고 그 값을 표시, json.address.city 값들 확인.
                const opt = document.createElement('option');
                opt.innerText = json.address.city;
                opt.value = json.address.city;

                console.log(json);

                region = document.getElementById('region');
                region.appendChild(opt);
                region.value = json.address.city;
            })
            .catch((error) => this.regionFetchError('Failed to fetch'));
    }

    static regionFetchError(errorMessage) { // error 콜백 함수.
        console.error(errorMessage);

        return this.default;
    }
}

// refernce - https://w3c.github.io/geolocation-api/#get-current-position, https://geocode.maps.co

// init
console.log(region);
Location.initRegionSelectBox();