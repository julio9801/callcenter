PrimeFaces.locales['es'] = {
    closeText: 'Cerrar',
    prevText: 'Anterior',
    nextText: 'Siguiente',
    monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
        'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre',
        'Diciembre'],
    monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago',
        'Sep', 'Oct', 'Nov', 'Dic'],
    dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves',
        'Viernes', 'Sábado'],
    dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
    dayNamesMin: ['D', 'L', 'M', 'X', 'J', 'V', 'S'],
    weekHeader: 'Semana',
    firstDay: 1,
    isRTL: false,
    showMonthAfterYear: false,
    yearSuffix: '',
    timeOnlyTitle: 'Sólo hora',
    timeText: 'Tiempo',
    hourText: 'Hora',
    minuteText: 'Minuto',
    secondText: 'Segundo',
    currentText: 'Fecha actual',
    ampm: false,
    month: 'Mes',
    week: 'Semana',
    day: 'Día',
    allDayText: 'Todo el día'
};

function goToTop() {
    window.scrollTo(0, 0);
}

$(document).ready(function () {

    $('.ui-datatable tbody td a').click(function (e) {
        PF('statusDialog').show();
    });

});



$(document).on("keypress", "#formCliente input:text", function (event) {
    if (event.keyCode == 13) {
        event.preventDefault();
        return false;
    }
});


$(document).on("blur", ":input[type=text]:not(.normalText,[type=password])", function () {
    console.log('Valido Campos')
    console.log(this.value)
    if (this.value.length > 0) {
        var inVal = this.value.match(/[`~!#%^&*()_|+\-=?;:'"<>\{\}\[\]\\\/]/gi);
        console.log(inVal)
        var err = "Los siguientes caracteres no son permitidos en el campo : `~!#%^&*()_|+\-=?;:' <>\{\}\[\]\\\/"
        if (inVal === null) {
            return true;
        } else {
            alert(err);
            this.value = '';
            this.value = '';
            return false;
        }
    }
});

$(document).on("blur", ":input[type=number]", function () {
    console.log('Valido Numero');
    if (this.value.length > 0) {
        var valNumber = /^[0-9]+$/;
        if (this.value.match(valNumber)) {
            if (this.value > 0)
                return true;
        }
        alert("Solo se aceptan numero positivos en los campos");
        this.value = '';
        this.value = '';
        return false;
    }
});

$('textarea').on("blur", ":input[type=textfield]", function () {
    console.log('Valido Textfield');
    if (this.value.length > 0) {
        var inVal = this.value.match(/[`~!#%^&*()_|+\-=?;:'"<>\{\}\[\]\\\/]/gi);
        console.log(inVal)
        var err = "Los siguientes caracteres no son permitidos en el campo: `~!#%^&*()_|+\-=?;:' <>\{\}\[\]\\\/"
        if (inVal === null) {
            return true;
        } else {
            alert(err);
            this.value = '';
            this.value = '';
            return false;
        }
    }
});


$(document).ready(function () {
    if (window.location.port === "33799") {
        $('.sb-image').remove();
        var str = "<img class='sb-image i1' src='../resources/images/sandbox.gif'>";
        $('#header-wrapper').append(str);

        setTimeout(function () {
            if ($('.i1').height() < 17) {
                $('.sb-image').remove();
                str = "<img class='sb-image i2' src='../../resources/images/sandbox.gif'>";
                $('#header-wrapper').append(str);
            }
        }, 500);
    }
});





$(document).ready(function () {
    if ($('.nav_logo_main img')[0].clientHeight < 20)
        $('.nav_logo_main img').attr('src', '../' + $('.nav_logo_main img').attr('src'))
});

$(document).ready(function () {
    if (window.location.pathname.includes('consultaQueja')) {
        
        if ($($('.user-control')[0]).text() > 1) {
            $($('.ui-accordion').find('h3.ui-accordion-header')[1]).css('display', 'none')
        }
    }
});


$(document).ready(function () {
    if (window.location.pathname.includes('altaQuejaInformacion')) {
        $('.control').each((i, el) => el.style.display = 'none');
        console.log('0');
        if ($('.control')[0].innerHTML.length === 0) {
            console.log('01');
            $('.queja-btn').each((i, el) => el.style.display = 'none');
        } else {
            console.log('02');
            $('.queja-btn').each((i, el) => el.style.display = 'block');
        }

        $('body').on('DOMSubtreeModified', function () {
            console.log('0a');
            if ($('.control')[0].innerHTML.length === 0) {
                console.log('0b');
                $('.queja-btn').each((i, el) => el.style.display = 'none');
            } else {
                console.log('0c');
                $('.queja-btn').each((i, el) => el.style.display = 'block');
            }
        });
    }
});
