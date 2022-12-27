$(document).ready(function() {
	//variabili globali
	let listaArtistiCorrente = null;

	/* INSERIMENTO DI UN ARTISTA */
	$('#insertArtistBtn').click(function(event) {
		event.preventDefault();
		
		let nome = $('#nome').val();
		let dataNascita = $('#dataNascita').val();
		let annoInizio = Math.abs($('#annoInizio').val());
		let annoFine = $('#annoFine').val();
		
		if (nome) {
			if (dataNascita) {
				if (annoInizio) {
					if (annoFine && annoFine >= annoInizio || !annoFine) {
						//preparo l'oggetto da inviare al server
						let params = {
							nome: nome, //key: value
							dataNascita: dataNascita,
							annoInizio: annoInizio,
							annoFine: annoFine
						};
						
						//invio di params al server con AJAX
						$.post('InsertArtistServlet', params, function(data) {
							//codice che dice cosa fare quando il server risponde
							alert(data);
							//converto la stringa JSON in un oggetto JS
							//let dataObject = JSON.parse(data);
						});
					} else {
						alert('L\'anno di fine attivita\' non puo\' essere minore dell\'anno di inizio'); //NEW
					}
					
				} else {
					alert('Devi inserire l\'anno di inizio attivita\'');
				}
			} else {
				alert('Devi inserire la data di nascita dell\'artista');
			}	
		} else {
			alert('Devi inserire il nome dell\'artista');
		}
	});
	
	/* INSERIMENTO DI UN ALBUM */
	$('#insertAlbumBtn').click(function(event) {
		event.preventDefault();
		
		let nomeAlbum = $('#nomeAlbum').val();
		let dataUscita = $('#dataUscita').val();
		let numTracce = Math.abs($('#numTracce').val());
		let idArtista = $('#artistList').val();
		let artista = null;
		listaArtistiCorrente.forEach(function(item) {
			if (item.id == idArtista) {
				artista = {
					id: item.id,
					nome: item.nome,
					dataNascita: item.dataNascita,
					annoInizio: item.annoInizio,
					annoFine: item.annoFine
				}
			}
		});
		
		if (nomeAlbum) {
			if (artista == null) {
				alert('Sicuro di non voler inserire l\'artista?');
			}
			// preparo l'oggetto da inviare al server
			let params = {
				nomeAlbum: nomeAlbum, // key: value
				dataUscita: dataUscita,
				numTracce: numTracce,
				artista: JSON.stringify(artista)
			};
					
			// invio di params al server con AJAX
			$.post('InsertAlbumServlet', params, function(data) {
				// codice che dice cosa fare quando il server risponde
				alert(data);
			});			
		} else {
			alert('Devi inserire il nome dell\'album');
		} 

	});
	
	/* OTTENIMENTO DI TUTTI GLI ARTISTI */
	/* si può migliorare prendendo eventuali caratteri inseriti nel nome */
	$(window).on('load', function() {
		$.get('GetAllArtistsServlet', function(data) {
			//converto la stringa JSON in un oggetto JS
			let dataObject = JSON.parse(data);
			listaArtistiCorrente = dataObject.content; //lista che contiene tutti gli artisti
			console.log('messaggio = ' + dataObject.message); //debug
			console.log('listaArtistiCorrente = ' + JSON.stringify(listaArtistiCorrente)); //debug
			//aggiunge gli artisti alla select del form (solo il nome e l'id come valore)
			listaArtistiCorrente.forEach(function(item) {
				$('#artistList').append('<option value="' + item.id + '">' + item.nome + '</option>');
			});
		});
	});	
	
});